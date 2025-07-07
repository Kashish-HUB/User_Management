<h1 align="center">🌐 User Management System</h1>

<p align="center">
  <b>A modern, full-stack REST API built with Spring Boot for managing users using PostgreSQL.</b><br><br>
  <img src="https://img.shields.io/badge/Java-17-blue.svg" />
  <img src="https://img.shields.io/badge/SpringBoot-2.7.5-brightgreen.svg" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue" />
  <img src="https://img.shields.io/badge/License-MIT-lightgrey.svg" />
  <img src="https://img.shields.io/badge/Build-Passing-success" />
</p>

---

## 🚀 Overview

The **User Management System** is a robust, RESTful application built with **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**. It offers complete CRUD operations, real-time frontend integration, validation, pagination, and testing support.

---

## 📂 Branches

| Branch Name              | Purpose                                 |
|--------------------------|------------------------------------------|
| `main`                   | Stable production code                   |
| `feature/enhancement`    | New feature additions                    |
| `repositoryTesting`      | Repository layer testing                 |
| `testing`                | General test cases (unit/integration)   |
| `Frontend_Integration`   | Frontend UI development                 |
| `Lombok_Implementation`  | Java model cleanup with Lombok          |

---

## 🛠️ Tech Stack

| Layer      | Technology                     |
|------------|--------------------------------|
| Language   | Java 17                        |
| Framework  | Spring Boot 2.7.5              |
| Build Tool | Maven                          |
| Database   | PostgreSQL                     |
| ORM        | Spring Data JPA + Hibernate    |
| Frontend   | HTML5, CSS3, JavaScript        |
| Testing    | JUnit 5, MockMvc               |
| Dev Tools  | VS Code (Frontend), IntelliJ (Backend) |

---

## 📦 Project Structure

User_Management/
│

├── controller/ # REST controllers

├── model/ # JPA entities

├── repository/ # Spring Data interfaces

├── resources/ # Properties & configs

├── tests/ # Unit & integration tests

└── Application.java # Spring Boot launcher


---

## ⚙️ Installation Guide

### ✅ Prerequisites

- Java 17+
- Maven
- PostgreSQL (Running locally)

### 🔧 Setup

```bash
# 1. Clone the repository
git clone https://github.com/Kashish-HUB/User_Management.git
cd User_Management

# 2. Create the PostgreSQL database
CREATE DATABASE user_management;

# 3. Update DB credentials in application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_management
spring.datasource.username=your_username
spring.datasource.password=your_password

# 4. Run the application
mvn spring-boot:run
# Server will start at: http://localhost:8080

```


## 🔌 API Endpoints

| Method | Endpoint          | Description           | Body |
| ------ | ----------------- | --------------------- | ---- |
| GET    | `/api/users`      | Get all users         | ❌    |
| GET    | `/api/users/page` | Paginated users       | ❌    |
| GET    | `/api/users/{id}` | Get user by ID        | ❌    |
| POST   | `/api/users`      | Add one or more users | ✅    |
| PUT    | `/api/users/{id}` | Update a user         | ✅    |
| DELETE | `/api/users/{id}` | Delete user by ID     | ❌    |

### 🧪 Testing

| Type        | Tool    | Description               |
| ----------- | ------- | ------------------------- |
| Unit Tests  | JUnit 5 | Entity & validation tests |
| Integration | MockMvc | Full endpoint testing     |

# Run all tests
mvn test

### 💻 Frontend Integration (VS Code)

Branch: Frontend_Integration

Location: VS Code

Stack: HTML5 + CSS3 + JavaScript

Features:

Realtime form validation

AJAX fetch with loading states

Elegant UI with CSS transitions

Pagination-ready interface

### 📁 Folder Example:

User_management(VS Code)

├── index.html

├── css/

│   └── style.css

└── js/
    └── script.js

### 🧩 Lombok Integration (IntelliJ)

Branch: Lombok_Implementation

Uses:

@Getter, @Setter

@AllArgsConstructor, @NoArgsConstructor

@Builder

Cleaner, boilerplate-free entity/model classes

✅ Ensure Lombok plugin is installed in IntelliJ.

### 📸 Screenshots

<p align="center"> <img src="https://user-images.githubusercontent.com/your-image1.png" width="600" alt="User Table UI" /> <img src="https://user-images.githubusercontent.com/your-image2.png" width="600" alt="Form Interface" /> </p>

### 👤 Author
Kashish Choudhary
📌 GitHub
🔗 LinkedIn

### 💡 Contributions
Feel free to fork this repository and contribute by submitting a pull request.

🌟 Support
If you find this project useful, please consider giving it a ⭐ on GitHub.

