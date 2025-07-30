package com.analytics.studentperformanceanalytics.controller;

import com.analytics.studentperformanceanalytics.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getStudentAnalytics(@PathVariable Long studentId) {
        try {
            Map<String, Object> analytics = analyticsService.getStudentPerformanceAnalytics(studentId);
            return new ResponseEntity<>(analytics, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/class")
    public ResponseEntity<Map<String, Object>> getClassAnalytics() {
        Map<String, Object> analytics = analyticsService.getOverallClassAnalytics();
        return new ResponseEntity<>(analytics, HttpStatus.OK);
    }
} 