package com.example.demo.service;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@Service
public interface FileStorageInterface {
    public void saveMultipartFile(MultipartFile file);
    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
