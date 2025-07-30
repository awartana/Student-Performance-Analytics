#!/bin/bash

# Student Performance Analytics API Test Script
# Make sure your Spring Boot application is running on localhost:8080

BASE_URL="http://localhost:8080"

echo "🚀 Student Performance Analytics API Test"
echo "=========================================="

# Function to print section headers
print_section() {
    echo ""
    echo "📌 $1"
    echo "-----------------------------------"
}

# Function to pause for readability
pause() {
    sleep 1
}

print_section "1. STUDENT MANAGEMENT"

echo "Creating students..."
echo "Student 1:"
curl -s -X POST $BASE_URL/api/students \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "email": "john.doe@example.com"}' | jq '.'
pause

echo "Student 2:"
curl -s -X POST $BASE_URL/api/students \
  -H "Content-Type: application/json" \
  -d '{"name": "Jane Smith", "email": "jane.smith@example.com"}' | jq '.'
pause

echo "Student 3:"
curl -s -X POST $BASE_URL/api/students \
  -H "Content-Type: application/json" \
  -d '{"name": "Mike Johnson", "email": "mike.johnson@example.com"}' | jq '.'
pause

echo "Getting all students:"
curl -s -X GET $BASE_URL/api/students | jq '.'
pause

print_section "2. ATTENDANCE MANAGEMENT"

echo "Recording attendance for John Doe (ID: 1)..."
curl -s -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{"date": "2024-01-15", "present": true, "student": {"id": 1}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{"date": "2024-01-16", "present": true, "student": {"id": 1}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{"date": "2024-01-17", "present": false, "student": {"id": 1}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{"date": "2024-01-18", "present": true, "student": {"id": 1}}' | jq '.'
pause

echo "Recording attendance for Jane Smith (ID: 2)..."
curl -s -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{"date": "2024-01-15", "present": true, "student": {"id": 2}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/attendance \
  -H "Content-Type: application/json" \
  -d '{"date": "2024-01-16", "present": true, "student": {"id": 2}}' | jq '.'
pause

echo "Getting attendance for Student 1:"
curl -s -X GET $BASE_URL/api/attendance/student/1 | jq '.'
pause

echo "Getting attendance rate for Student 1:"
curl -s -X GET $BASE_URL/api/attendance/student/1/rate
echo ""
pause

print_section "3. TEST SCORE MANAGEMENT"

echo "Adding test scores for John Doe (ID: 1)..."
curl -s -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{"subject": "Mathematics", "score": 85.5, "student": {"id": 1}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{"subject": "Science", "score": 92.0, "student": {"id": 1}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{"subject": "English", "score": 78.5, "student": {"id": 1}}' | jq '.'
pause

echo "Adding test scores for Jane Smith (ID: 2)..."
curl -s -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{"subject": "Mathematics", "score": 91.0, "student": {"id": 2}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{"subject": "Science", "score": 88.5, "student": {"id": 2}}' | jq '.'
pause

curl -s -X POST $BASE_URL/api/test-scores \
  -H "Content-Type: application/json" \
  -d '{"subject": "English", "score": 95.0, "student": {"id": 2}}' | jq '.'
pause

echo "Getting test scores for Student 1:"
curl -s -X GET $BASE_URL/api/test-scores/student/1 | jq '.'
pause

echo "Getting average score for Student 1:"
curl -s -X GET $BASE_URL/api/test-scores/student/1/average
echo ""
pause

echo "Getting Mathematics average for Student 1:"
curl -s -X GET $BASE_URL/api/test-scores/student/1/average/Mathematics
echo ""
pause

print_section "4. ANALYTICS"

echo "Getting comprehensive analytics for Student 1:"
curl -s -X GET $BASE_URL/api/analytics/student/1 | jq '.'
pause

echo "Getting comprehensive analytics for Student 2:"
curl -s -X GET $BASE_URL/api/analytics/student/2 | jq '.'
pause

echo "Getting class analytics:"
curl -s -X GET $BASE_URL/api/analytics/class | jq '.'
pause

print_section "5. UPDATE OPERATIONS"

echo "Updating Student 1 information:"
curl -s -X PUT $BASE_URL/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "John Updated Doe", "email": "john.updated@example.com"}' | jq '.'
pause

echo "Updating a test score (ID: 1):"
curl -s -X PUT $BASE_URL/api/test-scores/1 \
  -H "Content-Type: application/json" \
  -d '{"subject": "Mathematics", "score": 87.0}' | jq '.'
pause

print_section "FINAL SUMMARY"

echo "All students:"
curl -s -X GET $BASE_URL/api/students | jq '.'

echo ""
echo "All test scores:"
curl -s -X GET $BASE_URL/api/test-scores | jq '.'

echo ""
echo "All attendance records:"
curl -s -X GET $BASE_URL/api/attendance | jq '.'

echo ""
echo "🎉 API Testing Complete!"
echo "========================================" 