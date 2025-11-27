package com.example.student_service.controller;

import com.example.student_service.model.University;
import com.example.student_service.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    //  Ajouter une université
    @PostMapping("/add")
    public University addUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }

    // 📋 Lister toutes les universités
    @GetMapping("/getAll")
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}
