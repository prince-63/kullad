package com.kullad.services.impl;

import com.kullad.exceptions.ResourceNotFoundException;
import com.kullad.models.Product;
import com.kullad.repositories.ProductRepository;
import com.kullad.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        log.info("Saving product {}", product.toString());
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        log.info("Getting product with id: {}", productId);
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        log.info("Deleting product with id: {}", productId);
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProductStock(Long productId, Integer quantity) {
        log.info("Updating product with id: {}", productId);
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        Product currentProduct = getProductById(productId);
        currentProduct.setProductQuantity(currentProduct.getProductQuantity() + quantity);
        productRepository.save(currentProduct);
    }
}
