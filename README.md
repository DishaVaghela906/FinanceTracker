# Personal Finance Tracker

A Spring Boot REST API for managing personal finances, including income and expense tracking with secure user authentication using JWT tokens.

## Overview

Personal Finance Tracker is a RESTful web application built with Spring Boot that allows users to:
- Register and manage their accounts securely
- Track income and expenses
- Maintain a comprehensive financial record
- Authenticate using JWT tokens

The application follows best practices for security, validation, and RESTful API design.

## Features

✅ **User Authentication & Authorization**
- User registration with validation
- Secure login with JWT token generation
- Password encryption using Spring Security

✅ **Financial Management**
- Income tracking and management
- Expense tracking and management
- View financial records

✅ **Security**
- JWT-based token authentication
- Spring Security integration
- Input validation on all endpoints
- Password encryption

✅ **Development Tools**
- Spring Boot DevTools for faster development
- Maven build system
- Comprehensive error handling

## Tech Stack

- **Framework:** Spring Boot 4.0.7
- **Language:** Java 25
- **Database:** MySQL 8.0
- **Authentication:** JWT (JJWT 0.11.5)
- **Security:** Spring Security
- **ORM:** Spring Data JPA
- **Validation:** Spring Validation
- **Build Tool:** Maven
- **Testing:** Spring Boot Test

## Prerequisites

Before running this project, ensure you have:

- **Java 25** or higher installed
- **MySQL 8.0** or higher running locally
- **Maven 3.6+** installed
- **Git** (optional, for cloning)

## Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Personal-Finance-Tracker
```

### 2. Create MySQL Database

```sql
CREATE DATABASE FinanceTracker;
```

### 3. Update Application Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=Personal-Finance-Tracker

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/FinanceTracker
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT Configuration
jwt.secret=your-secret-key-at-least-384-bits-long
```

> **Important:** Replace database credentials and JWT secret with your own values. Never commit sensitive credentials to version control.

### 4. Build the Application

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Configuration

### JWT Secret Key

For security, generate a strong JWT secret key:

```bash
# Generate a base64 encoded 512-bit secret
openssl rand -base64 64
```

Update the `jwt.secret` property in `application.properties` with your generated key.

### Database Properties

| Property | Description | Example |
|----------|-------------|---------|
| `spring.datasource.url` | MySQL connection URL | `jdbc:mysql://localhost:3306/FinanceTracker` |
| `spring.datasource.username` | Database username | `root` |
| `spring.datasource.password` | Database password | `your_password` |
| `spring.jpa.hibernate.ddl-auto` | Auto schema generation | `update` or `create` |

## API Endpoints

### User Management

#### Register User
```
POST /users/register
Content-Type: application/json

{
  "userName": "John Doe",
  "userEmail": "john@example.com",
  "password": "securePassword123"
}

Response: 201 Created
{
  "message": "User registered successfully"
}
```

#### Login User
```
POST /users/login
Content-Type: application/json

{
  "userEmail": "john@example.com",
  "password": "securePassword123"
}

Response: 200 OK
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "Login successful"
}
```

### Income Management

#### Add Income
```
POST /income
Authorization: Bearer {token}
Content-Type: application/json

{
  "incomeAmount": 5000.00,
  "incomeSource": "Salary",
  "incomeDate": "2026-06-12"  # ISO format (YYYY-MM-DD)
}

Response: 201 Created
{
  "message": "Income added with id: 1"
}
```

### Expense Management

#### Add Expense
```
POST /expense
Authorization: Bearer {token}
Content-Type: application/json

{
  "expenseAmount": 500.00,
  "expenseCategory": "Groceries",
  "expenseDate": "2026-06-12"  # ISO format (YYYY-MM-DD)
}

Response: 201 Created
{
  "message": "Expense added with id: 1"
}
```
Do not include `userId` in the request body — the server extracts the authenticated user's email from the JWT and associates the record with that user.

## Project Structure

```
Personal-Finance-Tracker/
├── src/
│   ├── main/
│   │   ├── java/SpringBoot/Personal_Finance_Tracker/
│   │   │   ├── controller/          # REST API Controllers
│   │   │   │   ├── UserController.java
│   │   │   │   ├── IncomeController.java
│   │   │   │   └── ExpenseController.java
│   │   │   ├── service/             # Business Logic
│   │   │   │   ├── UserService.java
│   │   │   │   └── JwtService.java
│   │   │   ├── repository/          # Data Access Layer
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── IncomeRepository.java
│   │   │   │   └── ExpenseRepository.java
│   │   │   ├── model/               # Data Models
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   └── LoginResponse.java
│   │   │   │   └── entity/          # JPA Entities
│   │   │   │       ├── UserEntity.java
│   │   │   │       ├── Income.java
│   │   │   │       └── Expense.java
│   │   │   ├── config/              # Configuration Classes
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── filter/              # Authentication Filters
│   │   │   │   └── JwtFilter.java
│   │   │   └── PersonalFinanceTrackerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/                        # Unit Tests
│       └── java/SpringBoot/Personal_Finance_Tracker/
├── pom.xml                          # Maven Configuration
├── mvnw                             # Maven Wrapper (Unix)
├── mvnw.cmd                         # Maven Wrapper (Windows)
└── README.md                        # This File
```

## Security

### Authentication Flow

1. User registers with email and password
2. Password is encrypted using Spring Security's PasswordEncoder
3. User logs in with credentials
4. Server validates and generates JWT token
5. Client includes token in Authorization header for protected endpoints
6. JwtFilter validates token on each request

### JWT Filter

The `JwtFilter` intercepts all requests and validates the JWT token before allowing access to protected endpoints.

The filter places the JWT subject (the user's email) into the Spring Security context as the principal. In controllers you can obtain the authenticated user's email with:

```java
String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
```

### Best Practices

- ✅ Passwords are encrypted and never stored in plain text
- ✅ JWT tokens have configurable expiration times
- ✅ All inputs are validated before processing
- ✅ SQL injection protected through JPA/Hibernate
- ✅ CORS can be configured in SecurityConfig

## Database Schema

```sql
CREATE TABLE User(
	user_id int auto_increment primary key,
    user_name varchar(50) not null,
    user_email varchar(100) not null,
    password varchar(255) not null
);

CREATE TABLE Expense(
	Expense_id int auto_increment primary key,
    user_id int not null,
    Expense_amount decimal(10,2) not null,
    Expense_category varchar(50) not null,
    Expense_date date not null,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Income(
	Income_id int auto_increment primary key,
    user_id int not null,
    Income_amount decimal(10,2) not null,
    Income_source varchar(50) not null,
    Income_date date not null,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
```

## Usage Examples

### Example 1: Complete User Registration & Login Flow

```bash
# Step 1: Register a new user
curl -X POST http://localhost:8080/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "Jane Smith",
    "userEmail": "jane@example.com",
    "password": "SecurePass123!"
  }'

# Step 2: Login and get JWT token
curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "userEmail": "jane@example.com",
    "password": "SecurePass123!"
  }'

# Response will contain JWT token
# {
#   "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
#   "message": "Login successful"
# }

# Step 3: Use token for authenticated requests
TOKEN="your_jwt_token_here"

curl -X POST http://localhost:8080/income \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "incomeAmount": 5000.00,
    "incomeSource": "Monthly Salary",
    "incomeDate": "2026-06-12"
  }'
```

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Troubleshooting

### Database Connection Error
- Ensure MySQL is running: `mysql -u root -p`
- Verify database credentials in `application.properties`
- Check if `FinanceTracker` database exists

### JWT Token Invalid
- Verify JWT secret key is set correctly
- Check token expiration
- Ensure token is included in Authorization header as `Bearer {token}`

### Port 8080 Already in Use
```bash
# Change port in application.properties
server.port=8081
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or suggestions, please open an issue in the repository or contact the development team.

---

**Last Updated:** June 2024  
**Version:** 0.0.1-SNAPSHOT  
**Status:** In Development
