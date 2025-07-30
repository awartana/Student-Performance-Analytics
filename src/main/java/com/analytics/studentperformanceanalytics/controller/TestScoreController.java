package com.analytics.studentperformanceanalytics.controller;

import com.analytics.studentperformanceanalytics.model.TestScore;
import com.analytics.studentperformanceanalytics.service.TestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-scores")
public class TestScoreController {

    @Autowired
    private TestScoreService testScoreService;

    @PostMapping
    public ResponseEntity<TestScore> addTestScore(@RequestBody TestScore testScore) {
        TestScore addedTestScore = testScoreService.addTestScore(testScore);
        return new ResponseEntity<>(addedTestScore, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestScore>> getAllTestScores() {
        List<TestScore> testScores = testScoreService.getAllTestScores();
        return new ResponseEntity<>(testScores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestScore> getTestScoreById(@PathVariable Long id) {
        Optional<TestScore> testScore = testScoreService.getTestScoreById(id);
        return testScore.map(ts -> new ResponseEntity<>(ts, HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<TestScore>> getTestScoresByStudentId(@PathVariable Long studentId) {
        List<TestScore> testScores = testScoreService.getTestScoresByStudentId(studentId);
        return new ResponseEntity<>(testScores, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestScore> updateTestScore(@PathVariable Long id, @RequestBody TestScore testScoreDetails) {
        try {
            TestScore updatedTestScore = testScoreService.updateTestScore(id, testScoreDetails);
            return new ResponseEntity<>(updatedTestScore, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestScore(@PathVariable Long id) {
        try {
            testScoreService.deleteTestScore(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/{studentId}/average")
    public ResponseEntity<Double> getAverageScore(@PathVariable Long studentId) {
        double averageScore = testScoreService.calculateAverageScore(studentId);
        return new ResponseEntity<>(averageScore, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/average/{subject}")
    public ResponseEntity<Double> getAverageScoreBySubject(@PathVariable Long studentId, @PathVariable String subject) {
        double averageScore = testScoreService.calculateAverageScoreBySubject(studentId, subject);
        return new ResponseEntity<>(averageScore, HttpStatus.OK);
    }
} 