package com.analytics.studentperformanceanalytics.service;

import com.analytics.studentperformanceanalytics.model.Attendance;
import com.analytics.studentperformanceanalytics.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance recordAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    public Attendance updateAttendance(Long id, Attendance attendanceDetails) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found with id: " + id));
        
        attendance.setDate(attendanceDetails.getDate());
        attendance.setPresent(attendanceDetails.isPresent());
        
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found with id: " + id));
        attendanceRepository.delete(attendance);
    }

    // Business logic method to calculate attendance rate for a student
    public double calculateAttendanceRate(Long studentId) {
        List<Attendance> attendanceList = attendanceRepository.findByStudentId(studentId);
        if (attendanceList.isEmpty()) {
            return 0.0;
        }
        
        long presentCount = attendanceList.stream()
                .mapToLong(attendance -> attendance.isPresent() ? 1 : 0)
                .sum();
        
        return (double) presentCount / attendanceList.size() * 100;
    }
} 