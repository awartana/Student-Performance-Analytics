package com.analytics.studentperformanceanalytics.repository;

import com.analytics.studentperformanceanalytics.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
} 