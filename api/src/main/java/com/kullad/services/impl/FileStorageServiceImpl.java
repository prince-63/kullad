package com.kullad.services.impl;

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
    public String storeFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }

            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath);

            log.info("File uploaded successfully: {}", filePath);
            return filePath.toString();
        } catch (Exception e) {
            log.error("Error uploading image to server: {}", e.getMessage());
            throw new RuntimeException("Error uploading image to server: " + e.getMessage());
        }
    }

    @Override
    public String getFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);

            if (Files.exists(filePath)) {
                log.info("File retrieved successfully: {}", filePath);
                return filePath.toString();
            } else {
                throw new ResourceNotFoundException("File not found: " + fileName);
            }
        } catch (Exception e) {
            log.error("Error retrieving file: {}", e.getMessage());
            throw new RuntimeException("Error retrieving file: " + e.getMessage());
        }
    }

    @Override
    public String deleteFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("File deleted successfully: {}", filePath);
                return "File deleted successfully: " + fileName;
            } else {
                throw new ResourceNotFoundException("File not found: " + fileName);
            }
        } catch (IOException e) {
            log.error("Error deleting file: {}", e.getMessage());
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }
}
