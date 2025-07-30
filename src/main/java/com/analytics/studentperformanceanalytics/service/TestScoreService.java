package com.analytics.studentperformanceanalytics.service;

import com.analytics.studentperformanceanalytics.model.TestScore;
import com.analytics.studentperformanceanalytics.repository.TestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestScoreService {

    @Autowired
    private TestScoreRepository testScoreRepository;

    public TestScore addTestScore(TestScore testScore) {
        return testScoreRepository.save(testScore);
    }

    public List<TestScore> getAllTestScores() {
        return testScoreRepository.findAll();
    }

    public List<TestScore> getTestScoresByStudentId(Long studentId) {
        return testScoreRepository.findByStudentId(studentId);
    }

    public Optional<TestScore> getTestScoreById(Long id) {
        return testScoreRepository.findById(id);
    }

    public TestScore updateTestScore(Long id, TestScore testScoreDetails) {
        TestScore testScore = testScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test score not found with id: " + id));
        
        testScore.setSubject(testScoreDetails.getSubject());
        testScore.setScore(testScoreDetails.getScore());
        
        return testScoreRepository.save(testScore);
    }

    public void deleteTestScore(Long id) {
        TestScore testScore = testScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test score not found with id: " + id));
        testScoreRepository.delete(testScore);
    }

    // Business logic method to calculate average score for a student
    public double calculateAverageScore(Long studentId) {
        List<TestScore> testScores = testScoreRepository.findByStudentId(studentId);
        if (testScores.isEmpty()) {
            return 0.0;
        }
        
        return testScores.stream()
                .mapToDouble(TestScore::getScore)
                .average()
                .orElse(0.0);
    }

    // Business logic method to calculate average score by subject for a student
    public double calculateAverageScoreBySubject(Long studentId, String subject) {
        List<TestScore> testScores = testScoreRepository.findByStudentId(studentId);
        
        return testScores.stream()
                .filter(ts -> ts.getSubject().equalsIgnoreCase(subject))
                .mapToDouble(TestScore::getScore)
                .average()
                .orElse(0.0);
    }
} 