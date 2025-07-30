package com.analytics.studentperformanceanalytics.model;

import jakarta.persistence.*;

@Entity
public class TestScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)  // Foreign key to Student table
    private Student student;

    // Constructors
    public TestScore() {
    }

    public TestScore(String subject, Double score, Student student) {
        this.subject = subject;
        this.score = score;
        this.student = student;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
} 