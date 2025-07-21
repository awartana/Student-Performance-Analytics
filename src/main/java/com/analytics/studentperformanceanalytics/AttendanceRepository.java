package com.analytics.studentperformanceanalytics.repository;

import com.analytics.studentperformanceanalytics.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}