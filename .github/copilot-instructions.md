# Copilot Instructions for JavaWeb Project

## Project Overview

This is a **Library Management System (图书管理系统)** - a Java web application for managing library operations including books, users, and borrowing records.

## Technology Stack

- **Language**: Java
- **Type**: Web Application
- **Project Name**: javaweb

## Code Style and Conventions

### General Guidelines

- Follow standard Java naming conventions:
  - Classes: PascalCase (e.g., `BookController`, `UserService`)
  - Methods and variables: camelCase (e.g., `getUserById`, `bookList`)
  - Constants: UPPER_SNAKE_CASE (e.g., `MAX_BORROW_DAYS`)
  - Packages: lowercase (e.g., `com.library.controller`)

### Best Practices

- Use meaningful and descriptive variable names
- Add JavaDoc comments for public classes and methods
- Keep methods focused and concise
- Follow the Single Responsibility Principle
- Use proper exception handling with try-catch blocks
- Validate user input to prevent security vulnerabilities

## Project Structure

```
javaweb/
├── .github/
│   └── copilot-instructions.md
└── README.md
```

## Development Guidelines

### Adding New Features

1. Follow the existing project structure and patterns
2. Ensure code is properly documented
3. Test new functionality thoroughly
4. Consider security implications, especially for user input and database operations

### Security Considerations

- Always sanitize user input to prevent SQL injection
- Use parameterized queries for database operations
- Implement proper authentication and authorization
- Validate file uploads if applicable
- Use HTTPS for sensitive operations

### Code Quality

- Write clean, readable code
- Remove commented-out code before committing
- Keep methods under 50 lines when possible
- Avoid code duplication - extract common logic into utility methods

## Language Notes

- The project includes Chinese language content (Simplified Chinese)
- Comments and documentation may be in Chinese or English
- User-facing text should be in Chinese for consistency

## Build and Test Instructions

*To be updated as the project develops with specific build tools (Maven/Gradle) and testing frameworks.*

## Additional Resources

- Follow Java EE or Spring Framework conventions if these technologies are adopted
- Refer to web security best practices for handling user authentication and session management
