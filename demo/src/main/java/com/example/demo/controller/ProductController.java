package com.example.demo.controller;

import com.example.demo.enitiy.Product;
import com.example.demo.service.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductInterface productRepo;
    @Value("${valuegiven}")
    private String value;

    @PostMapping("/Add")
    public Product addProduct(@RequestBody Product product){
        return productRepo.addProduct(product);
    }
    @GetMapping("/All")
    public List<Product> getAllProducts(){
        System.out.println("this is     ----->    "+value);
        return productRepo.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        return productRepo.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public String addProduct(@PathVariable String id){
        return productRepo.deleteProductById(id);
    }
}
