package com.kullad.services.impl;

import com.kullad.exceptions.ResourceAlreadyExistsException;
import com.kullad.exceptions.ResourceNotFoundException;
import com.kullad.services.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String storeImageFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            if (Files.exists(filePath)) {
                throw new ResourceAlreadyExistsException("Image file already exists with name: " + fileName);
            }
            Files.copy(file.getInputStream(), filePath);

            log.info("Image file uploaded successfully: {}", filePath);
            return fileName;
        } catch (Exception e) {
            log.error("Error uploading image to server: {}", e.getMessage());
            throw new RuntimeException("Error uploading image to server: " + e.getMessage());
        }
    }

    @Override
    public byte[] getImageFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);

            if (Files.exists(filePath)) {
                log.info("Image file retrieved successfully: {}", filePath);
                return Files.readAllBytes(filePath);
            } else {
                throw new ResourceNotFoundException("Image file not found: " + fileName);
            }
        } catch (Exception e) {
            log.error("Error retrieving file: {}", e.getMessage());
            throw new RuntimeException("Error retrieving file: " + e.getMessage());
        }
    }

    @Override
    public String deleteImageFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("File deleted successfully: {}", filePath);
                return "File deleted successfully: " + fileName;
            } else {
                throw new ResourceNotFoundException("Image file not found: " + fileName);
            }
        } catch (IOException e) {
            log.error("Error deleting file: {}", e.getMessage());
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }
}
