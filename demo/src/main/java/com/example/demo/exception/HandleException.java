package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException {
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler({CommonException.class})
    public Map<String, String> handleException(CommonException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }
@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoStudentsException.class})
    public Map<String, String> handleException(NoStudentsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message", ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(exc->{
            errorMap.put(exc.getField(),exc.getDefaultMessage());
                }
        );
        return errorMap;
    }
}
