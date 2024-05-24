package com.example.demo.service;

import com.example.demo.enitiy.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductInterface {
    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public Product getProductById(String id);
    public String deleteProductById(String id);
}
