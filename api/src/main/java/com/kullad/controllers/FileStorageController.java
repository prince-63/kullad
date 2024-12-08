package com.kullad.controllers;

import com.kullad.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File cannot be empty.");
        }
        try {
            String fileName = fileStorageService.storeFile(file);
            return ResponseEntity.ok(fileName);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<String> getFile(@PathVariable String fileName) {
        if (fileName.isEmpty()) {
            return ResponseEntity.badRequest().body("File name cannot be empty.");
        }
        try {
            String filePath = fileStorageService.getFile(fileName);
            return ResponseEntity.ok(filePath);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        if (fileName.isEmpty()) {
            return ResponseEntity.badRequest().body("File name cannot be empty.");
        }
        try {
            String message = fileStorageService.deleteFile(fileName);
            return ResponseEntity.ok(message);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
