package com.analytics.studentperformanceanalytics.controller;

import com.analytics.studentperformanceanalytics.model.Attendance;
import com.analytics.studentperformanceanalytics.repository.AttendanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;




import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance){
        return attendanceRepository.save(attendance);
    }
    @GetMapping
    public List<Attendance> getAttendance(){
        return attendanceRepository.findAll();
    }
}

