package com.example.demo.service;

import com.example.demo.enitiy.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomStudentService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository; // Assuming you have a repository for Student entity

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.getStudentByName(username).get(0);
        if (student == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        String password = student.getEmail();
        String encodedPassword = passwordEncoder.encode(password);
        return User
                .withUsername(username)
                .password(encodedPassword)
                .roles("USER")
                .build();
    }

}
