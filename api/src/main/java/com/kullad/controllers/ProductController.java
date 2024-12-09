package com.kullad.controllers;

import com.kullad.constants.StatusCodeConstants;
import com.kullad.constants.StatusMessageConstants;
import com.kullad.dto.ErrorResponseDTO;
import com.kullad.dto.ResponseDTO;
import com.kullad.mapper.ResponseMapper;
import com.kullad.models.Product;
import com.kullad.services.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, WebRequest request) {
        try {
            Product savedProduct = productService.saveProduct(product);
            ResponseDTO responseDTO = ResponseMapper.successResponseMapper(StatusCodeConstants.CREATED, StatusMessageConstants.CREATED, savedProduct);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponseDTO errorResponseDTO = ResponseMapper.errorResponseMapper(StatusCodeConstants.INTERNAL_SERVER_ERROR, e, request);
            log.error(errorResponseDTO.toString());
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "productId") Long productId, WebRequest request) {
        try {
            Product product = productService.getProductById(productId);
            ResponseDTO responseDTO = ResponseMapper.successResponseMapper(StatusCodeConstants.SUCCESS, StatusMessageConstants.SUCCESS, product);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseDTO errorResponseDTO = ResponseMapper.errorResponseMapper(StatusCodeConstants.INTERNAL_SERVER_ERROR, e, request);
            log.error(errorResponseDTO.toString());
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(WebRequest request) {
        try {
            List<Product> products = productService.getAllProducts();
            ResponseDTO responseDTO = ResponseMapper.successResponseMapper(StatusCodeConstants.SUCCESS, StatusMessageConstants.SUCCESS, products);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseDTO errorResponseDTO = ResponseMapper.errorResponseMapper(StatusCodeConstants.INTERNAL_SERVER_ERROR, e, request);
            log.error(errorResponseDTO.toString());
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") Long productId, WebRequest request) {
        try {
            productService.deleteProduct(productId);
            ResponseDTO responseDTO = ResponseMapper.successResponseMapper(StatusCodeConstants.SUCCESS, StatusMessageConstants.DELETED, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseDTO errorResponseDTO = ResponseMapper.errorResponseMapper(StatusCodeConstants.INTERNAL_SERVER_ERROR, e, request);
            log.error(errorResponseDTO.toString());
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
