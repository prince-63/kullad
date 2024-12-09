package com.kullad.controllers;

import com.kullad.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File cannot be empty.");
        }
        try {
            String fileName = fileStorageService.storeImageFile(file);
            return ResponseEntity.ok(fileName);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<byte[]> getImageFile(@PathVariable String fileName) {
        if (fileName.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            byte[] imageData = fileStorageService.getImageFile(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteImageFile(@PathVariable String fileName) {
        if (fileName.isEmpty()) {
            return ResponseEntity.badRequest().body("File name cannot be empty.");
        }
        try {
            String message = fileStorageService.deleteImageFile(fileName);
            return ResponseEntity.ok(message);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
