package com.kullad.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    String getFile(String fileName);
    String deleteFile(String fileName);
}
