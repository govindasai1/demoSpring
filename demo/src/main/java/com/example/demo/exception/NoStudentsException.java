package com.example.demo.exception;

public class NoStudentsException extends RuntimeException {
    public NoStudentsException(String message) {
        super(message);
    }
}
