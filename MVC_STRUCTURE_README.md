# Student Performance Analytics - MVC Architecture

## Overview
This Spring Boot application follows a proper **Model-View-Controller (MVC)** architecture for managing student performance data including attendance tracking and test scores with comprehensive analytics.

## Project Structure

```
src/main/java/com/analytics/studentperformanceanalytics/
├── StudentPerformanceAnalyticsApplication.java  # Main Spring Boot application
├── model/                                        # Data entities (Models)
│   ├── Student.java                             # Student entity
│   ├── Attendance.java                          # Attendance entity
│   └── TestScore.java                           # Test score entity
├── controller/                                  # REST API endpoints (Controllers)
│   ├── StudentController.java                   # Student management endpoints
│   ├── AttendanceController.java                # Attendance management endpoints
│   ├── TestScoreController.java                 # Test score management endpoints
│   └── AnalyticsController.java                 # Analytics endpoints
├── service/                                     # Business logic (Services)
│   ├── StudentService.java                      # Student business logic
│   ├── AttendanceService.java                   # Attendance business logic
│   ├── TestScoreService.java                    # Test score business logic
│   └── AnalyticsService.java                    # Analytics business logic
└── repository/                                  # Data access layer (Repositories)
    ├── StudentRepository.java                   # Student data access
    ├── AttendanceRepository.java                # Attendance data access
    └── TestScoreRepository.java                 # Test score data access
```

## Architecture Layers

### 1. **Model Layer** (`model/`)
- Contains JPA entities representing database tables
- Defines relationships between entities
- Includes validation annotations

### 2. **Controller Layer** (`controller/`)
- REST API endpoints
- HTTP request/response handling
- Proper HTTP status codes
- Input validation

### 3. **Service Layer** (`service/`)
- Business logic implementation
- Data processing and calculations
- Transaction management
- Error handling

### 4. **Repository Layer** (`repository/`)
- Data access using Spring Data JPA
- Custom query methods
- Database operations

## API Endpoints

### Student Management (`/api/students`)
- `POST /` - Create new student
- `GET /` - List all students
- `GET /{id}` - Get student by ID
- `PUT /{id}` - Update student
- `DELETE /{id}` - Delete student

### Attendance Management (`/api/attendance`)
- `POST /` - Record attendance
- `GET /` - Get all attendance records
- `GET /{id}` - Get specific attendance record
- `GET /student/{studentId}` - Get attendance by student
- `PUT /{id}` - Update attendance record
- `DELETE /{id}` - Delete attendance record
- `GET /student/{studentId}/rate` - Get attendance rate

### Test Scores (`/api/test-scores`)
- `POST /` - Add test score
- `GET /` - Get all test scores
- `GET /{id}` - Get specific test score
- `GET /student/{studentId}` - Get scores by student
- `PUT /{id}` - Update test score
- `DELETE /{id}` - Delete test score
- `GET /student/{studentId}/average` - Get average score
- `GET /student/{studentId}/average/{subject}` - Get subject average

### Analytics (`/api/analytics`)
- `GET /student/{studentId}` - Comprehensive student performance
- `GET /class` - Overall class analytics

## Key Improvements Made

### 1. **Proper MVC Structure**
- Separated concerns into distinct layers
- Clear package organization
- Proper dependency injection

### 2. **Service Layer Addition**
- Business logic extracted from controllers
- Reusable service methods
- Better error handling

### 3. **Enhanced Controllers**
- Proper HTTP responses with status codes
- Better error handling
- RESTful endpoint design

### 4. **Analytics Features**
- Attendance rate calculations
- Average score calculations
- Performance classification
- Class-wide analytics

### 5. **Repository Enhancements**
- Custom query methods
- Proper annotations
- Optimized data access

## Database Configuration
- **Database**: MySQL 8.0
- **Connection**: localhost:3306/student_performance
- **Hibernate**: Auto-update schema
- **Dialect**: MySQL8Dialect

## Performance Classification
The system automatically classifies student performance based on:
- **Excellent**: ≥90% attendance, ≥85% average score
- **Good**: ≥80% attendance, ≥75% average score  
- **Average**: ≥70% attendance, ≥65% average score
- **Below Average**: ≥60% attendance, ≥50% average score
- **Needs Improvement**: Below threshold values

## Benefits of This Structure

1. **Maintainability**: Clear separation of concerns
2. **Scalability**: Easy to add new features
3. **Testability**: Each layer can be tested independently
4. **Reusability**: Service layer methods can be reused
5. **Flexibility**: Easy to modify business logic without affecting other layers

## Running the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Next Steps for Enhancement
1. Add input validation annotations
2. Implement exception handling
3. Add authentication and authorization
4. Create unit and integration tests
5. Add API documentation (Swagger)
6. Implement caching for better performance 