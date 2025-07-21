package com.analytics.studentperformanceanalytics.controller;

import com.analytics.studentperformanceanalytics.model.TestScore;
import com.analytics.studentperformanceanalytics.repository.TestScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestScoreRepository testScoreRepository;

    @PostMapping
    public TestScore addTestScore(@RequestBody TestScore testScore) {
        return testScoreRepository.save(testScore);
    }

    @GetMapping
    public List<TestScore> getAllTestScores() {
        return testScoreRepository.findAll();
    }

    @GetMapping("/{id}")
    public TestScore getTestScoreById(@PathVariable Long id) {
        return testScoreRepository.findById(id).orElse(null);
    }
}