package com.example.demo.service;

import com.example.demo.dto.Department;
import com.example.demo.enitiy.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentInterface {
    String addStudent(Student student);
    Student getStudent(String stringUuid);
    List<Student> getAllStudents();
    String removeStudent(String stringUuid);
    Student editStudent(String stringUuid, Department studentDepartment);
    List<Student> getStudentByGender(String gender);
    Student assignCourse(String courseId, String studentId);
}
