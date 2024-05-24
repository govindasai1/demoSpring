package com.example.demo.service;

import com.example.demo.enitiy.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ProductService implements ProductInterface{
    @Autowired
    ProductRepository productRepo;
    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepo.findById(Integer.parseInt(id));
    }

    @Override
    public String deleteProductById(String id) {
        return productRepo.deleteById(Integer.parseInt(id));
    }
}
