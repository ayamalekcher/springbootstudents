package com.example.student_service.controller;

import com.example.student_service.model.Student;
import com.example.student_service.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.NoSuchElementException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student") // ✅ Même chemin que dans React

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ Récupérer tous les étudiants
    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // ✅ Ajouter un étudiant
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // ✅ Supprimer un étudiant (DELETE http://localhost:8888/student/{id})
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    // ✅ Mettre à jour un étudiant
    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    // 🔍 Rechercher un étudiant par nom
    @GetMapping("/search")
    public List<Student> searchStudent(@RequestParam String name) {
        return studentService.searchByName(name);
    }

     // ✅ Inscrire un étudiant à un cours
    @PostMapping("/{id}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable int id, @PathVariable int courseId) {
        try {
            studentService.enrollStudentInCourse(id, courseId);
            return ResponseEntity.ok("Student enrolled successfully in course!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student or Course not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error enrolling student: " + e.getMessage());
        }
    }
}
