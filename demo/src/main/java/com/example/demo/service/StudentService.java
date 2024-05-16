package com.example.demo.service;

import com.example.demo.dto.Department;
import com.example.demo.enitiy.Course;
import com.example.demo.enitiy.Student;
import com.example.demo.exception.CommonException;
import com.example.demo.helper.Helper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements StudentInterface {
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private Helper helper;
    ArrayList<Student> arrayList = new ArrayList<>();

    public String addStudent(Student s) {

//        arrayList.add(s);
        studentRepo.save(s);
        return "STUDENT ADDED SUCCESSFULLY WITH ID -> " + s;
    }

    public Student getStudent(String stringUuid) {
        UUID uuid = UUID.fromString(stringUuid);
        Student list = studentRepo.findById(uuid).orElse(null);

        if (list == null) {
            throw new CommonException("student not found .......");
        }
        return list;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        log.info("running getAllStudents "+students.get(0).getId());
        return helper.responseValidator(students);
    }

    public String removeStudent(String stringUuid) {
        List<Student> students = studentRepo.findAll();
        helper.responseValidator(students);
        UUID uuid = UUID.fromString(stringUuid);
        studentRepo.deleteById(uuid);
        return "STUDENT REMOVED SUCCESSFULLY...";
    }

    public Student editStudent(String stringUuid, Department studentDepartment) {
        UUID uuid = UUID.fromString(stringUuid);
        studentRepo.findById(uuid).map(student -> {
            student.setDepartment(studentDepartment.department);
            return studentRepo.save(student);
        });
        return studentRepo.findById(uuid).get();
    }

    public List<Student> getStudentByGender(String gender) {
        List<Student> students = studentRepo.getStudentByUser(gender);
        helper.responseValidator(students);
        return students;
    }

    @Override
    public Student assignCourse(String courseId, String studentId) {
        List<Course> courseList = null;
        Student student = getStudent(studentId);
        Course course = courseRepo.findById(UUID.fromString(courseId)).get();
        courseList = student.getCourses();
        courseList.add(course);
        student.setCourses(courseList);
        return studentRepo.save(student);
    }
}
