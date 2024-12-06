# Todo List API

## Project Description
This is a RESTful API for task management (Todo List) implemented with Spring Boot. The application provides CRUD operations (Create, Read, Update, Delete) for tasks, using PostgreSQL as the main database and Redis for caching, optimizing the performance of frequent read operations.

## Requirements
- Java JDK 17 or higher
- Docker
- Docker Compose
- Maven

## Installation Instructions

### 1. Clone the Repository
```bash
git clone [REPOSITORY_URL]
cd [DIRECTORY_NAME]
```

### 2. Install Dependencies
The project uses Maven for dependency management. The main dependencies include:
- Spring Boot 3.4.0
- Spring Data JPA
- Spring Data Redis
- PostgreSQL Driver
- Lombok
- Spring Boot Test
- Rest Assured (for testing)

Dependencies will be automatically installed when building the project.

### 3. Environment Variables
Environment variables are configured in the `compose.yml` file:
- `DATABASE_URL`: PostgreSQL connection URL
- `DATABASE_USERNAME`: PostgreSQL user
- `DATABASE_PASSWORD`: PostgreSQL password
- `SPRING_REDIS_HOST`: Redis host
- `SPRING_REDIS_PORT`: Redis port

## Local Execution

### 1. Using Docker Compose
```bash
# Start all services
docker compose up -d

# The application will be available at http://localhost:8080
```

This command will start:
- The Spring Boot application (port 8080)
- PostgreSQL (port 5434)
- Redis (port 6379)

### 2. Using Maven (Development)
```bash
# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

### API Endpoints
- `GET /api/tasks` - List all tasks
- `GET /api/tasks/{id}` - Get a specific task
- `POST /api/tasks` - Create a new task
- `PUT /api/tasks/{id}` - Update a task
- `DELETE /api/tasks/{id}` - Delete a task

## Testing

### Unit Tests
```bash
# Run unit tests
mvn test -Dtest=UnitTest
```
These tests check the business logic and individual services.

### Integration Tests
```bash
# Run integration tests with Redis
mvn test -Dtest=RedisTest
```
These tests verify the correct integration with Redis and the cache functionality.

### System Tests
```bash
# Run system tests
mvn test -Dtest=SystemTest
```
End-to-end tests that verify the complete operation of the application.

## Technologies Used

### Backend
- **Framework**: Spring Boot 3.4.0
- **Language**: Java 17
- **Dependency Management**: Maven

### Databases
- **Main**: PostgreSQL 15
    - Persistent storage for tasks
- **Cache**: Redis
    - Improves performance on read-heavy operations

### Containerization
- Docker
- Docker Compose
    - Service orchestration
    - Development environment configuration

### Testing
- JUnit
- Spring Boot Test
- Rest Assured
- Embedded Redis (for testing)
- H2 Database (for testing)

### Development Tools
- Lombok
- Spring Data JPA
- Spring Web
- Maven Wrapper

### Made by Valeria Martinez (No one was hurt during this project :D) 
