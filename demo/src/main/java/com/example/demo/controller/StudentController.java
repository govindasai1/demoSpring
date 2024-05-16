package com.example.demo.controller;

import com.example.demo.dto.Department;
import com.example.demo.dto.StudentRequest;
import com.example.demo.dto.Token;
import com.example.demo.enitiy.Course;
import com.example.demo.enitiy.Student;
import com.example.demo.helper.Helper;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.service.CourseInterface;
import com.example.demo.service.StudentInterface;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentInterface studentService;
    @Autowired
    private CourseInterface courseInterface;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private Helper helper;
    @PostMapping("/auth")
    public Token authenticateStudent(@RequestBody StudentRequest request){
        helper.doAuthentication(request.name,request.email);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.name);
        String token = jwtHelper.generateToken(userDetails);
        return new Token(token);
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody @Valid Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Student> getStudent(@PathVariable String uuid) {
        return ResponseEntity.ok(studentService.getStudent(uuid));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Student>> getgenderStudents(@PathVariable String gender) {
        return ResponseEntity.ok(studentService.getStudentByGender(gender));
    }

    @DeleteMapping("/{uuid}")
    public String removeStudent(@PathVariable String uuid) {
        return studentService.removeStudent(uuid);
    }

    @PutMapping("/{uuid}")
        public Student editStudent(@PathVariable String uuid, @RequestBody Department studentDepartment) {
        return studentService.editStudent(uuid, studentDepartment);
    }

    @PostMapping("/cources/add")
    public String addCourse(@RequestBody Course course){
        return courseInterface.addingCourse(course);
    }
    @GetMapping("/cources/all")
    public List<Course> allCourse(){
        return courseInterface.getAllCourses();
    }
    @DeleteMapping("/cources/{uuid}")
    public String deleteCourse(@PathVariable String uuid){
        return courseInterface.DeleteCourse(uuid);
    }
    @PutMapping("/assign/{courceId}/cources/{studentId}")
    public Student allocateCourse(@PathVariable String courceId,@PathVariable String studentId){
        return studentService.assignCourse(courceId,studentId);
    }
}
