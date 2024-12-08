//package com.kullad.controllers;
//
//import com.kullad.services.CloudinaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/images")
//public class CloudinaryController {
//
//    @Autowired
//    private CloudinaryService cloudinaryService;
//
//    @PostMapping
//    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("File cannot be empty.");
//        }
//        try {
//            Map<String, String> response = cloudinaryService.uploadImage(file);
//            return ResponseEntity.ok(response);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Image upload failed. Please try again later.");
//        }
//    }
//
//    @GetMapping("/{imageId}")
//    public ResponseEntity<?> getImageUrl(@PathVariable String imageId) {
//        try {
//            String imageUrl = cloudinaryService.getImageUrl(imageId);
//            return ResponseEntity.ok(Map.of("imageId", imageId, "imageUrl", imageUrl));
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body("Image not found: " + e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{imageId}")
//    public ResponseEntity<?> deleteImage(@PathVariable String imageId) {
//        try {
//            boolean isDeleted = cloudinaryService.deleteImage(imageId);
//            if (isDeleted) {
//                return ResponseEntity.ok(Map.of("message", "Image deleted successfully", "imageId", imageId));
//            } else {
//                return ResponseEntity.status(404).body("Image not found for deletion.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Failed to delete image: " + e.getMessage());
//        }
//    }
//}
//
