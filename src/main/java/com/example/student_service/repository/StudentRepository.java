package com.example.student_service.repository;

import com.example.student_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {  // <-- ID نوعه Integer
    List<Student> findByFirstNameContainingIgnoreCase(String name);
    List<Student> findByUniversity_Name(String university);
}
