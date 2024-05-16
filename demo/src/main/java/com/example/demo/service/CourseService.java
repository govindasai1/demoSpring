package com.example.demo.service;

import com.example.demo.enitiy.Course;
import com.example.demo.helper.Helper;
import com.example.demo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService implements CourseInterface {
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private Helper helper;

    @Override
    public String addingCourse(Course course) {
        courseRepo.save(course);
        return "COURSE SAVES.....";
    }

    @Override
    @Cacheable(cacheNames = "Course")
    public List<Course> getAllCourses() {
        log.info("getAllCorses running..");
        return helper.responseValidator2(courseRepo.findAll());
    }

    @Override
    @CacheEvict(cacheNames = "Course",key = "#uuid")
    public String DeleteCourse(String uuid) {
        courseRepo.deleteById(UUID.fromString(uuid));
        return "DELETED SUCCESSFULLY...";
    }

}
