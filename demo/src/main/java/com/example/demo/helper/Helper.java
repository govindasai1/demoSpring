package com.example.demo.helper;

import com.example.demo.enitiy.Course;
import com.example.demo.enitiy.Student;
import com.example.demo.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helper {
    @Autowired
    private AuthenticationManager manager;
    public List<Student> responseValidator(List<Student> list){
        if (list.isEmpty()){
            throw new CommonException("NO STUDENTS AVAILABLE ....");
        }else {
            return list;
        }
    }
    public List<Course> responseValidator2(List<Course> list){
        if (list.isEmpty()){
            throw new CommonException("NO STUDENTS AVAILABLE ....");
        }else {
            return list;
        }
    }

    public void doAuthentication(String name,String email){
        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(name,email);
        try {
            manager.authenticate(auth);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException(" Invalid name or email !!");
        }
    }
}
