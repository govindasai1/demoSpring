package com.example.demo.service;

import com.example.demo.exception.CommonException;
import jakarta.annotation.Resource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStorageService implements FileStorageInterface{
    private final Path root = Paths.get("uploads");
    @Override
    public void saveMultipartFile(MultipartFile file) {
        try {
            Files.createDirectories(root);
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        }catch (IOException exc){
             throw new CommonException("folder creation error!!");
        }catch (Exception e){
            if (e instanceof FileAlreadyExistsException){
                throw new CommonException("file already exist!!");
            }
            throw new CommonException("Exception occurred");
        }
    }

    @Override
    public Resource load(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
