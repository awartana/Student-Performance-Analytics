package com.analytics.studentperformanceanalytics.repository;

import com.analytics.studentperformanceanalytics.model.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestScoreRepository extends JpaRepository<TestScore, Long> {

    // Optional: Custom finder method to get all scores by student ID
    List<TestScore> findByStudentId(Long studentId);
}