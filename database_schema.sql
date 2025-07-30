-- Student Performance Analytics Database Schema
-- Database: student_performance
-- MySQL 8.0 Compatible

-- Create database (if not exists)
CREATE DATABASE IF NOT EXISTS student_performance;
USE student_performance;

-- 1. Create Student table
CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Create Attendance table
CREATE TABLE IF NOT EXISTS attendance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    present BOOLEAN NOT NULL DEFAULT FALSE,
    student_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign key constraint
    CONSTRAINT fk_attendance_student 
        FOREIGN KEY (student_id) 
        REFERENCES student(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    
    -- Composite unique constraint to prevent duplicate attendance records for same student on same date
    UNIQUE KEY uk_attendance_student_date (student_id, date)
);

-- 3. Create Test Score table
CREATE TABLE IF NOT EXISTS test_score (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(255) NOT NULL,
    score DOUBLE NOT NULL,
    student_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign key constraint
    CONSTRAINT fk_test_score_student 
        FOREIGN KEY (student_id) 
        REFERENCES student(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    
    -- Check constraint to ensure score is within valid range (0-100)
    CONSTRAINT chk_test_score_range 
        CHECK (score >= 0 AND score <= 100)
);

-- Create indexes for better query performance
CREATE INDEX idx_attendance_student_id ON attendance(student_id);
CREATE INDEX idx_attendance_date ON attendance(date);
CREATE INDEX idx_attendance_student_date ON attendance(student_id, date);

CREATE INDEX idx_test_score_student_id ON test_score(student_id);
CREATE INDEX idx_test_score_subject ON test_score(subject);
CREATE INDEX idx_test_score_student_subject ON test_score(student_id, subject);

-- Insert some sample data (optional)
INSERT INTO student (name, email) VALUES 
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Mike Johnson', 'mike.johnson@example.com'),
('Sarah Wilson', 'sarah.wilson@example.com'),
('David Brown', 'david.brown@example.com');

-- Sample attendance data
INSERT INTO attendance (student_id, date, present) VALUES 
(1, '2024-01-15', true),
(1, '2024-01-16', true),
(1, '2024-01-17', false),
(2, '2024-01-15', true),
(2, '2024-01-16', true),
(2, '2024-01-17', true),
(3, '2024-01-15', false),
(3, '2024-01-16', true),
(3, '2024-01-17', true);

-- Sample test score data
INSERT INTO test_score (student_id, subject, score) VALUES 
(1, 'Mathematics', 85.5),
(1, 'Science', 92.0),
(1, 'English', 78.5),
(2, 'Mathematics', 91.0),
(2, 'Science', 88.5),
(2, 'English', 95.0),
(3, 'Mathematics', 76.0),
(3, 'Science', 82.5),
(3, 'English', 88.0);

-- Display table structures
SHOW CREATE TABLE student;
SHOW CREATE TABLE attendance;
SHOW CREATE TABLE test_score; 