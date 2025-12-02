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
        String url = "https://djangocourses-1.onrender.com/courses/studentcourses/add/";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, Object> data = new HashMap<>();
        data.put("student_id", studentId);
        data.put("course", courseId); // ⚠ مهم !! Django يستعمل "course" وليس "course_id"

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Django Response: " + response.getBody());
    }
}

