//package com.kullad.config;
//
//import com.cloudinary.Cloudinary;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class CloudinaryConfig {
//
//    @Value("${cloudinary.api-key}")
//    private String apiKey;
//
//    @Value("${cloudinary.api-secret}")
//    private String apiSecret;
//
//    @Value("${cloudinary.cloud-name}")
//    private String cloudName;
//
//    @Bean
//    public Cloudinary getCloudinary() {
//        Map<String, String> config = new HashMap<>();
//        config.put("cloud_name", cloudName);
//        config.put("api_key", apiKey);
//        config.put("api_secret", apiSecret);
//
//        return new Cloudinary(config);
//    }
//
//    @PostConstruct
//    public void validateCloudinaryConfig() {
//        if (apiKey.isBlank() || apiSecret.isBlank() || cloudName.isBlank()) {
//            throw new IllegalStateException("Cloudinary configuration is missing or invalid");
//        }
//    }
//}
