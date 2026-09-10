Smart AI E-Commerce Platform

A backend e-commerce project built using Java, Spring Boot, and MySQL.

I'm building this project step by step to strengthen my backend development skills and gain practical experience with REST APIs, database integration, validation, and application structure.

Current Status

Backend development in progress

The following parts have been implemented so far:

- Product management
- Category management
- REST APIs
- MySQL database integration
- Request and Response DTOs
- Input validation
- Exception handling
- Product search
- Pagination
- Sorting
- Secure database configuration
- API testing with Postman
- Git and GitHub version control

Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman
- Git & GitHub
- Eclipse

Project Structure

The backend follows a simple layered structure:

src
└── main
    └── java
        └── ...
            ├── controller
            ├── service
            ├── repository
            ├── entity
            └── dto

The main flow of the application is:

Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL

I have kept the different responsibilities separate so that the project is easier to understand and maintain.

Product Module

The Product module currently supports:

- Create a product
- Get a product by ID
- Get all products
- Update a product
- Delete a product
- Search products
- Pagination
- Sorting
- Request DTO
- Response DTO
- Input validation
- Product not-found handling

Category Module

The Category module currently supports:

- Create a category
- Get a category by ID
- Get all categories
- Update a category
- Delete a category

Database

The project uses MySQL with Spring Data JPA for database operations.

I have also avoided storing the actual database password in the project.

The password is read from an environment variable:

spring.datasource.password=${DB_PASSWORD}

This keeps the database password outside the source code and prevents it from being pushed to GitHub.

API Testing

I use Postman to test the REST APIs.

The APIs have been tested for:

- Creating and updating products
- Retrieving products
- Deleting products
- Product search
- Pagination
- Sorting
- Validation
- Product not-found cases
- Category operations

Running the Project

Requirements

Before running the project, make sure you have:

- Java
- MySQL
- Maven
- An IDE such as Eclipse
- Postman (for API testing)

Basic Setup

1. Clone the repository.
2. Open the project in your IDE.
3. Create the required MySQL database.
4. Configure the database connection.
5. Set the "DB_PASSWORD" environment variable with your local MySQL password.
6. Run the Spring Boot application.
7. Test the APIs using Postman.

Git & GitHub

Git is being used throughout development to track the project changes.

I am committing the project at meaningful development milestones rather than keeping all changes in a single commit.

What I'm Learning Through This Project

While building this project, I'm getting practical experience with:

- Designing REST APIs
- Spring Boot application structure
- Spring Data JPA
- MySQL database operations
- DTOs
- Validation
- Exception handling
- Search, pagination, and sorting
- API testing
- Environment variables and basic security practices
- Git and GitHub


Built with: Java, Spring Boot and MySQL

Project status: In development