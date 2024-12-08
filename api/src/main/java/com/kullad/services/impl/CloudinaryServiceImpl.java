//package com.kullad.services.impl;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.kullad.services.CloudinaryService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@Service
//public class CloudinaryServiceImpl implements CloudinaryService {
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    @Override
//    public Map<String, String> uploadImage(MultipartFile file)  {
//        try {
//            String imageId = UUID.randomUUID().toString();
//            var uploadParams = ObjectUtils.asMap(
//                    "public_id", "images/" + imageId,
//                    "overwrite", true
//            );
//
//            var uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
//
//            return Map.of(
//                    "imageId", imageId,
//                    "imageUrl", (String) uploadResult.get("secure_url")
//            );
//        } catch (IOException e) {
//            log.error("Error uploading image to Cloudinary: {}", e.getMessage());
//            throw new RuntimeException("Failed to upload image.", e);
//        }
//    }
//
//    @Override
//    public String getImageUrl(String imageId) {
//        return cloudinary.url()
//                .publicId("images/" + imageId)
//                .generate();
//    }
//
//    @Override
//    public Boolean deleteImage(String imageId) {
//        try {
//            var deleteResult = cloudinary.uploader()
//                    .destroy("images/" + imageId, ObjectUtils.emptyMap());
//
//            return "ok".equals(deleteResult.get("result"));
//        }
//        catch (Exception e) {
//            log.error("Error deleting image from Cloudinary: {}", e.getMessage());
//            throw new RuntimeException("Failed to delete image.", e);
//        }
//    }
//}
