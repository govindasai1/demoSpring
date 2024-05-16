package com.example.demo.repository;

import com.example.demo.enitiy.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM Student s WHERE s.gender = :gender")
    public List<Student> getStudentByUser(@Param("gender") String gender);

    @Query("SELECT s FROM Student s WHERE s.name = :name")
    public List<Student> getStudentByName(@Param("name") String name);
}
