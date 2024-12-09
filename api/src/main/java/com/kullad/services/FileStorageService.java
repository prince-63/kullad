package com.kullad.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeImageFile(MultipartFile file);
    byte[] getImageFile(String fileName);
    String deleteImageFile(String fileName);
}
