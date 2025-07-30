package com.analytics.studentperformanceanalytics.repository;

import com.analytics.studentperformanceanalytics.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
    // Custom query to find attendance by student ID
    List<Attendance> findByStudentId(Long studentId);
} 