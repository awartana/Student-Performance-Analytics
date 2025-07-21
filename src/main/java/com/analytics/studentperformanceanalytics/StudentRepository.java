package com.analytics.studentperformanceanalytics.repository;

import com.analytics.studentperformanceanalytics.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}