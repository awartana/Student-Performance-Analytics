package com.analytics.studentperformanceanalytics.repository;

import com.analytics.studentperformanceanalytics.model.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestScoreRepository extends JpaRepository<TestScore, Long> {

    // Custom finder method to get all scores by student ID
    List<TestScore> findByStudentId(Long studentId);
} 