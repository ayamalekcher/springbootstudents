package com.example.student_service.service;

import com.example.student_service.model.Student;
import com.example.student_service.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DjangoCourseClient courseClient;

    public StudentService(StudentRepository studentRepository, DjangoCourseClient courseClient) {
        this.studentRepository = studentRepository;
        this.courseClient = courseClient;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {   // <-- ID ك int
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {   // <-- ID ك int
        studentRepository.deleteById(id);
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByFirstNameContainingIgnoreCase(name);
    }

    public void enrollStudentInCourse(int studentId, int courseId) {  // <-- int
        courseClient.enrollStudentInCourse(studentId, courseId);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
}
