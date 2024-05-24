package com.example.demo.repository;

import com.example.demo.enitiy.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    @Autowired
    private RedisTemplate redisTemplate;
    String hashValue = "Product";

    public Product save(Product product) {
        redisTemplate.opsForHash().put(hashValue, product.getId(), product);
        return product;
    }
    public List<Product> findAll(){
        return redisTemplate.opsForHash().values(hashValue);
    }
    public Product findById(int id){
return (Product) redisTemplate.opsForHash().get(hashValue,id);
    }
    public String deleteById(int id){
        redisTemplate.opsForHash().delete(hashValue,id);
        return "PRODUCT DELETED";
    }
}
