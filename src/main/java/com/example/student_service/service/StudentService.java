package com.example.student_service.service;

import com.example.student_service.model.Student;
import com.example.student_service.model.University;
import com.example.student_service.repository.StudentRepository;
import com.example.student_service.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DjangoCourseClient courseClient;
    private final UniversityRepository universityRepository;

    public StudentService(StudentRepository studentRepository,
                          DjangoCourseClient courseClient,
                          UniversityRepository universityRepository) {
        this.studentRepository = studentRepository;
        this.courseClient = courseClient;
        this.universityRepository = universityRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {

        // 🔥 Vérifier si l'université existe
        int uniId = student.getUniversity().getId();

        University university = universityRepository.findById(uniId)
                .orElseThrow(() -> new RuntimeException("University not found with id = " + uniId));

        // 🔥 Assigner l'université trouvée
        student.setUniversity(university);

        // 🔥 Enregistrer l'étudiant
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByFirstNameContainingIgnoreCase(name);
    }

     public void enrollStudentInCourse(int studentId, int courseId) {
        // ✅ هنا نتحقق أن الطالب موجود
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id = " + studentId));

        // ✅ Call to Django service
        courseClient.enrollStudentInCourse(studentId, courseId);
    }

    public Student updateStudent(Student student) {

        int uniId = student.getUniversity().getId();

        University university = universityRepository.findById(uniId)
                .orElseThrow(() -> new RuntimeException("University not found with id = " + uniId));

        student.setUniversity(university);

        return studentRepository.save(student);
    }
}
