# Student Performance Analytics - API Testing with cURL

## Base URL
```bash
BASE_URL="http://localhost:8080"
```

---

## 1. 👨‍🎓 Student Management (`/api/students`)

### Create Students
```bash
# Create Student 1
curl -X POST $BASE_URL/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com"
  }'

# Create Student 2
curl -X POST $BASE_URL/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Smith", 
    "email": "jane.smith@example.com"
  }'

# Create Student 3
curl -X POST $BASE_URL/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mike Johnson",
    "email": "mike.johnson@example.com"
  }'
```

### Get All Students
```bash
curl -X GET $BASE_URL/api/students
```

### Get Student by ID
```bash
# Get student with ID 1
curl -X GET $BASE_URL/api/students/1

# Get student with ID 2  
curl -X GET $BASE_URL/api/students/2
```

### Update Student
```bash
# Update student with ID 1
curl -X PUT $BASE_URL/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Updated Doe",
    "email": "john.updated@example.com"
  }'
```

### Delete Student
```bash
# Delete student with ID 3
curl -X DELETE $BASE_URL/api/students/3
```

---

## 2. 📅 Attendance Management (`/api/attendance`)

### Record Attendance
```bash
# Record attendance for Student 1 - Present
curl -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-15",
    "present": true,
    "student": {"id": 1}
  }'

# Record attendance for Student 1 - Absent
curl -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-16", 
    "present": false,
    "student": {"id": 1}
  }'

# Record attendance for Student 2 - Present
curl -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-15",
    "present": true,
    "student": {"id": 2}
  }'

# Multiple attendance records for Student 1
curl -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-17",
    "present": true,
    "student": {"id": 1}
  }'

curl -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-18",
    "present": true,
    "student": {"id": 1}
  }'
```

### Get All Attendance Records
```bash
curl -X GET $BASE_URL/api/attendance
```

### Get Attendance by ID
```bash
# Get attendance record with ID 1
curl -X GET $BASE_URL/api/attendance/1
```

### Get Attendance by Student ID
```bash
# Get all attendance records for student 1
curl -X GET $BASE_URL/api/attendance/student/1

# Get all attendance records for student 2
curl -X GET $BASE_URL/api/attendance/student/2
```

### Update Attendance
```bash
# Update attendance record ID 1
curl -X PUT $BASE_URL/api/attendance/1 \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-01-15",
    "present": true
  }'
```

### Delete Attendance
```bash
# Delete attendance record ID 2
curl -X DELETE $BASE_URL/api/attendance/2
```

### Get Attendance Rate
```bash
# Get attendance rate for student 1
curl -X GET $BASE_URL/api/attendance/student/1/rate

# Get attendance rate for student 2
curl -X GET $BASE_URL/api/attendance/student/2/rate
```

---

## 3. 📝 Test Score Management (`/api/test-scores`)

### Add Test Scores
```bash
# Add Mathematics score for Student 1
curl -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "Mathematics",
    "score": 85.5,
    "student": {"id": 1}
  }'

# Add Science score for Student 1
curl -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "Science",
    "score": 92.0,
    "student": {"id": 1}
  }'

# Add English score for Student 1
curl -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "English", 
    "score": 78.5,
    "student": {"id": 1}
  }'

# Add Mathematics score for Student 2
curl -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "Mathematics",
    "score": 91.0,
    "student": {"id": 2}
  }'

# Add Science score for Student 2
curl -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "Science",
    "score": 88.5,
    "student": {"id": 2}
  }'
```

### Get All Test Scores
```bash
curl -X GET $BASE_URL/api/test-scores
```

### Get Test Score by ID
```bash
# Get test score with ID 1
curl -X GET $BASE_URL/api/test-scores/1
```

### Get Test Scores by Student ID
```bash
# Get all test scores for student 1
curl -X GET $BASE_URL/api/test-scores/student/1

# Get all test scores for student 2
curl -X GET $BASE_URL/api/test-scores/student/2
```

### Update Test Score
```bash
# Update test score ID 1
curl -X PUT $BASE_URL/api/test-scores/1 \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "Mathematics",
    "score": 87.0
  }'
```

### Delete Test Score
```bash
# Delete test score ID 3
curl -X DELETE $BASE_URL/api/test-scores/3
```

### Get Average Scores
```bash
# Get overall average score for student 1
curl -X GET $BASE_URL/api/test-scores/student/1/average

# Get overall average score for student 2
curl -X GET $BASE_URL/api/test-scores/student/2/average
```

### Get Average Score by Subject
```bash
# Get Mathematics average for student 1
curl -X GET $BASE_URL/api/test-scores/student/1/average/Mathematics

# Get Science average for student 1
curl -X GET $BASE_URL/api/test-scores/student/1/average/Science

# Get English average for student 1
curl -X GET $BASE_URL/api/test-scores/student/1/average/English
```

---

## 4. 📊 Analytics (`/api/analytics`)

### Get Student Performance Analytics
```bash
# Get comprehensive analytics for student 1
curl -X GET $BASE_URL/api/analytics/student/1

# Get comprehensive analytics for student 2
curl -X GET $BASE_URL/api/analytics/student/2
```

### Get Class Analytics
```bash
# Get overall class performance analytics
curl -X GET $BASE_URL/api/analytics/class
```

---

## 🔧 Complete Test Sequence

### Run this complete sequence to test the full application flow:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080"

echo "🚀 Starting API Tests..."

echo "1️⃣ Creating Students..."
curl -X POST $BASE_URL/api/students -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}'
curl -X POST $BASE_URL/api/students -H "Content-Type: application/json" -d '{"name": "Jane Smith", "email": "jane.smith@example.com"}'

echo -e "\n2️⃣ Recording Attendance..."
curl -X POST $BASE_URL/api/attendance -H "Content-Type: application/json" -d '{"date": "2024-01-15", "present": true, "student": {"id": 1}}'
curl -X POST $BASE_URL/api/attendance -H "Content-Type: application/json" -d '{"date": "2024-01-16", "present": true, "student": {"id": 1}}'
curl -X POST $BASE_URL/api/attendance -H "Content-Type: application/json" -d '{"date": "2024-01-17", "present": false, "student": {"id": 1}}'

echo -e "\n3️⃣ Adding Test Scores..."
curl -X POST $BASE_URL/api/test-scores -H "Content-Type: application/json" -d '{"subject": "Mathematics", "score": 85.5, "student": {"id": 1}}'
curl -X POST $BASE_URL/api/test-scores -H "Content-Type: application/json" -d '{"subject": "Science", "score": 92.0, "student": {"id": 1}}'

echo -e "\n4️⃣ Getting Analytics..."
curl -X GET $BASE_URL/api/analytics/student/1
curl -X GET $BASE_URL/api/analytics/class

echo -e "\n✅ API Tests Complete!"
```

---

## 📋 Expected Response Formats

### Student Response
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "attendanceList": [...],
  "testScores": [...]
}
```

### Analytics Response
```json
{
  "studentId": 1,
  "studentName": "John Doe",
  "studentEmail": "john.doe@example.com",
  "attendanceRate": 66.67,
  "averageScore": 88.75,
  "performanceGrade": "Below Average"
}
```

### Class Analytics Response
```json
{
  "totalStudents": 2,
  "classAttendanceRate": 75.0,
  "classAverageScore": 89.0
}
```

---

## 🛠️ Quick Testing Tips

1. **Start your application first**: `mvn spring-boot:run`
2. **Create students before adding attendance/scores**
3. **Use the student IDs returned from creation**
4. **Check HTTP status codes for errors**
5. **Use `-v` flag for verbose output**: `curl -v ...` 