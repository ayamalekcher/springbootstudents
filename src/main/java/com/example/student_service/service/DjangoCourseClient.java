package com.example.student_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@Service
public class DjangoCourseClient {

    private final RestTemplate restTemplate = new RestTemplate();

    // 🎓 Méthode pour inscrire un étudiant à un cours (appel Django)
    public void enrollStudentInCourse(int studentId, int courseId) {
        String url = "http://localhost:8000/studentcourses/add/"; // Django endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, Object> data = new HashMap<>();
        data.put("student_id", studentId);
        data.put("course_id", courseId);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("✅ Django Response: " + response.getBody());
    }

    // (Optionnel) Si tu veux plus tard ramener la liste des cours d'un étudiant
    public Object getCoursesForStudent(int studentId) {
        String url = "http://localhost:8000/courses/student/" + studentId;
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        return response.getBody();
    }
}
