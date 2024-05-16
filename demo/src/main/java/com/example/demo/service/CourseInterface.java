package com.example.demo.service;

import com.example.demo.enitiy.Course;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
@Service
public interface CourseInterface {
    public String addingCourse(Course course);
    public List<Course> getAllCourses();
    public String DeleteCourse(String uuid);
}
