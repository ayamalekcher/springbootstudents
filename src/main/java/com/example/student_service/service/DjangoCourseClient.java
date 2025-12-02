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

    public void enrollStudentInCourse(int studentId, int courseId) {
        String url = "http://localhost:9090/courses/studentcourses/add/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> studentMap = new HashMap<>();
        Map<String, Object> courseMap = new HashMap<>();

        studentMap.put("id", studentId);
        courseMap.put("id", courseId);

        data.put("student", studentMap);
        data.put("course", courseMap);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println("✅ Django Response: " + response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Error calling Django Course Service: " + e.getMessage());
        }
    }
}
