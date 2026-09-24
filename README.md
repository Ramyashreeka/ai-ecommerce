Smart AI E-Commerce

A backend-focused e-commerce application built using Java, Spring Boot, and MySQL. The project is being developed step by step to build practical backend development skills and create a portfolio-ready application.

Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman
- Git & GitHub
- Eclipse

Current Features

Product Management

- Create a product
- Get all products
- Get a product by ID
- Update a product
- Delete a product
- Search products
- Pagination
- Sorting
- Request validation
- Product not-found handling

Category Management

- Create a category
- Get all categories
- Get a category by ID
- Update a category
- Delete a category

Product–Category Relationship

- A product can be associated with a category.
- Product responses include category details when a category is assigned.
- Products without an assigned category return "null" for the category.

User Management

- Create User
- Get User by ID
- Get All Users
- Update User
- Delete User
- User input validation
- Email uniqueness validation
- Custom exception handling for user-related errors
- Password excluded from User API responses

API Testing

The REST APIs are tested using Postman.

Database

The application uses MySQL for persistent data storage.

The main entities currently include:

- Product
- Category

Products and categories are connected through a category relationship.

Project Structure

src
└── main
    ├── java
    │   └── com.example.smart_ecommerce
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── exception
    │       ├── repository
    │       └── service
    │
    └── resources
        └── application.properties



