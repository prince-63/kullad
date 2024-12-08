package com.kullad.services;

import com.kullad.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    void deleteProduct(Long productId);

    void updateProductStock(Long productId, Integer quantity);
}
