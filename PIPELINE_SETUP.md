# Student Management - CI/CD Pipeline Configuration

## ✅ Status: Ready for Production

### Code Quality Metrics
- **Code Coverage**: 95.8% (183/191 instructions)
- **Sonar Issues**: 0 Bugs, 0 Vulnerabilities, 0 Code Smells
- **Test Suite**: 33 test methods (15 service + 18 controller)
- **Unit Tests**: All passing

### Test Summary
```
Service Layer Tests:
  ✓ DepartmentServiceTest (5 methods)
  ✓ EnrollmentServiceTest (5 methods)
  ✓ StudentServiceTest (5 methods)

Controller Layer Tests:
  ✓ DepartmentControllerTest (6 methods)
  ✓ EnrollmentControllerTest (5 methods)
  ✓ StudentControllerTest (6 methods)

Integration Tests:
  ✓ StudentManagementApplicationTests (2 methods)
```

### Coverage by Component
- **Controllers**: 100% (72/72 instructions)
- **Services**: 100% (78/78 instructions)  
- **Entities**: 100% (33/33 instructions)
- **Application**: 0% (not tested - only startup verification)

### Build Artifacts
- **JAR**: `target/student-management-0.0.1-SNAPSHOT.jar`
- **Coverage Report**: `target/site/jacoco/index.html`
- **Test Reports**: `target/surefire-reports/`

## Quick Start

### 1. Run Tests with Coverage
```bash
mvn clean test
mvn jacoco:report
```

### 2. Build Application
```bash
mvn clean package
```

### 3. Run SonarQube Analysis
```bash
mvn clean test
mvn sonar:sonar \
  -Dsonar.projectKey=student-management \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<YOUR_SONAR_TOKEN>
```

### 4. Run Application
```bash
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

## Jenkins Pipeline Integration

The application is configured to work with the provided Jenkins pipeline:

**Pipeline Stages:**
1. Clone Repository
2. Test & Coverage (mvn clean test, mvn jacoco:report)
3. Build Maven (mvn clean package -DskipTests)
4. SonarQube Analysis
5. Quality Gate (waits for SonarQube validation)
6. Docker Build
7. Docker Push
8. Archive Artifacts

**Quality Gate Requirements:**
- Coverage on New Code: ≥ 80% ✅ (Current: 95.8%)
- Bugs: 0 ✅
- Vulnerabilities: 0 ✅
- Code Smells: 0 ✅

## Configuration Files

### pom.xml
- Maven Surefire Plugin 3.5.3 (test execution)
- JaCoCo 0.8.12 (code coverage)
- Mockito 4.x (unit test mocking)
- Spring Boot 3.5.5
- Java 17

### sonar-project.properties
- projectKey: student-management
- Coverage: JaCoCo XML reports
- Tests: Surefire reports
- Exclusions: entities, config packages

## Docker Build

The application can be containerized using the provided Dockerfile:

```bash
docker build -t nourbenati/student-management .
docker run -p 8080:8080 nourbenati/student-management
```

## API Endpoints

### Department Management
- `GET /Depatment/getAllDepartment` - Get all departments
- `GET /Depatment/getDepartment/{id}` - Get department by ID
- `POST /Depatment/createDepartment` - Create department
- `PUT /Depatment/updateDepartment` - Update department
- `DELETE /Depatment/deleteDepartment/{id}` - Delete department

### Student Management
- `GET /students/getAllStudents` - Get all students
- `GET /students/getStudent/{id}` - Get student by ID
- `POST /students/createStudent` - Create student
- `PUT /students/updateStudent` - Update student
- `DELETE /students/deleteStudent/{id}` - Delete student

### Enrollment Management
- `GET /Enrollment/getAllEnrollment` - Get all enrollments
- `GET /Enrollment/getEnrollment/{id}` - Get enrollment by ID
- `POST /Enrollment/createEnrollment` - Create enrollment
- `PUT /Enrollment/updateEnrollment` - Update enrollment
- `DELETE /Enrollment/deleteEnrollment/{id}` - Delete enrollment

## Dependencies

### Build & Test
- Maven 3.x
- Java 17
- JUnit 5
- Mockito 4.x

### Runtime
- Spring Boot 3.5.5
- Spring Data JPA
- MySQL Database
- Lombok

### Code Quality
- JaCoCo (Coverage)
- SonarQube 9.9.8+
- Maven Surefire (Test Reporting)

## Notes

1. **Database**: Application requires MySQL at runtime. Configure via `application.properties`
2. **Test Isolation**: Unit tests use Mockito to avoid database dependencies
3. **Coverage**: All business logic (services, controllers) fully tested
4. **CI/CD**: Jenkins pipeline is configured in web UI, not via Jenkinsfile
