package com.analytics.studentperformanceanalytics.service;

import com.analytics.studentperformanceanalytics.model.Student;
import com.analytics.studentperformanceanalytics.repository.AttendanceRepository;
import com.analytics.studentperformanceanalytics.repository.StudentRepository;
import com.analytics.studentperformanceanalytics.repository.TestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AnalyticsService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private TestScoreService testScoreService;

    public Map<String, Object> getStudentPerformanceAnalytics(Long studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }

        Student student = studentOpt.get();
        Map<String, Object> analytics = new HashMap<>();
        
        // Student basic info
        analytics.put("studentId", student.getId());
        analytics.put("studentName", student.getName());
        analytics.put("studentEmail", student.getEmail());
        
        // Attendance analytics
        double attendanceRate = attendanceService.calculateAttendanceRate(studentId);
        analytics.put("attendanceRate", attendanceRate);
        
        // Academic performance
        double averageScore = testScoreService.calculateAverageScore(studentId);
        analytics.put("averageScore", averageScore);
        
        // Performance classification
        String performanceGrade = classifyPerformance(attendanceRate, averageScore);
        analytics.put("performanceGrade", performanceGrade);
        
        return analytics;
    }

    public Map<String, Object> getOverallClassAnalytics() {
        Map<String, Object> classAnalytics = new HashMap<>();
        
        // Get all students for class-wide analytics
        var allStudents = studentRepository.findAll();
        
        if (allStudents.isEmpty()) {
            classAnalytics.put("message", "No students found");
            return classAnalytics;
        }
        
        int totalStudents = allStudents.size();
        double totalAttendanceRate = 0.0;
        double totalAverageScore = 0.0;
        
        int studentsWithAttendance = 0;
        int studentsWithScores = 0;
        
        for (Student student : allStudents) {
            double attendanceRate = attendanceService.calculateAttendanceRate(student.getId());
            double averageScore = testScoreService.calculateAverageScore(student.getId());
            
            if (attendanceRate > 0) {
                totalAttendanceRate += attendanceRate;
                studentsWithAttendance++;
            }
            
            if (averageScore > 0) {
                totalAverageScore += averageScore;
                studentsWithScores++;
            }
        }
        
        classAnalytics.put("totalStudents", totalStudents);
        classAnalytics.put("classAttendanceRate", studentsWithAttendance > 0 ? totalAttendanceRate / studentsWithAttendance : 0.0);
        classAnalytics.put("classAverageScore", studentsWithScores > 0 ? totalAverageScore / studentsWithScores : 0.0);
        
        return classAnalytics;
    }

    private String classifyPerformance(double attendanceRate, double averageScore) {
        if (attendanceRate >= 90 && averageScore >= 85) {
            return "Excellent";
        } else if (attendanceRate >= 80 && averageScore >= 75) {
            return "Good";
        } else if (attendanceRate >= 70 && averageScore >= 65) {
            return "Average";
        } else if (attendanceRate >= 60 && averageScore >= 50) {
            return "Below Average";
        } else {
            return "Needs Improvement";
        }
    }
} 