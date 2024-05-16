package com.example.demo.enitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@Table(name="Student_Info")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "name should not be empty")
    private String name;
    @NotNull(message = "department should not be empty")
    private String department;
    private String email;
    private String mobileNumber;
    private String gender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Book_Fk_id")
    private Book book;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Student_Fk_id",referencedColumnName = "id")
    private List<Address> address;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}

