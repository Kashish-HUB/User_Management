<h1 align="center">🌐 User Management System</h1>

<p align="center">
  <b>A powerful Spring Boot REST API for managing users with full CRUD operations, validations, and PostgreSQL integration.</b><br>
  <br>
  <img src="https://img.shields.io/badge/Java-17-blue.svg">
  <img src="https://img.shields.io/badge/SpringBoot-2.7.5-green.svg">
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue">
  <img src="https://img.shields.io/badge/License-MIT-lightgrey.svg">
  <img src="https://img.shields.io/badge/Build-Success-brightgreen.svg">
</p>

---

## 📁 Table of Contents

| Section | Description |
|--------|-------------|
| 🚀 [Project Overview](#-project-overview) | Introduction & features |
| 🛠️ [Tech Stack](#️-tech-stack) | Technologies used |
| 📦 [Project Structure](#-project-structure) | Folder breakdown |
| ⚙️ [Installation Guide](#️-installation-guide) | Clone, config, run |
| 📬 [API Endpoints](#-api-endpoints) | All available APIs |
| 🧪 [Testing](#-testing) | Unit/Integration testing |
| 📸 [Screenshots](#-screenshots) | Visuals of API usage |
| ✍️ [Author](#️-author) | GitHub & LinkedIn |
| 🪪 [License](#-license) | MIT License |
| 🌟 [Support](#-support) | Show some love |

---

## 🚀 Project Overview

> A RESTful User Management System that enables you to create, fetch, update, delete users and supports pagination, input validation, and proper error handling — built using **Spring Boot + PostgreSQL**.

### ✨ Core Features

- 📥 Create, Read, Update, Delete Users  
- 🔍 Get users by ID or paginated list  
- 🛡️ Field validation using Hibernate Validator  
- ⏱️ Auto timestamping for created/updated fields  
- 📤 Integration tests using MockMvc  
- 🧩 Modular code structure  

---

## 🛠️ Tech Stack

| Layer       | Technology                 |
|------------|-----------------------------|
| Language    | Java 17                    |
| Framework   | Spring Boot 2.7.5          |
| Build Tool  | Maven                      |
| Database    | PostgreSQL                 |
| ORM         | Spring Data JPA + Hibernate|
| Testing     | JUnit 5 + MockMvc          |
| JSON Mapper | Jackson (JavaTimeModule)   |
| Version Control | Git + GitHub          |

---

## 📦 Project Structure
User_Management/
├── controller/ → REST API endpoints
├── model/ → User entity class
├── repository/ → JPA repository
├── test/ → Unit & integration tests
└── resources/
└── application.properties


---

## ⚙️ Installation Guide

### ✅ Prerequisites

- Java 17+
- Maven
- PostgreSQL running locally

### 🔧 Setup Steps

```bash
# 1. Clone the repository
git clone https://github.com/Kashish-HUB/User_Management.git
cd User_Management

# 2. Configure PostgreSQL (create DB)
CREATE DATABASE user_management;

# 3. Set DB config in src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_management
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🚀 Run the App
# Start the server
mvn spring-boot:run
Server will be running at: http://localhost:8080
```

## 📬 API Endpoints
| Method | Endpoint          | Description             | Request Body  |
| ------ | ----------------- | ----------------------- | ------------- |
| GET    | `/api/users`      | Get all users           | ❌             |
| GET    | `/api/users/{id}` | Get user by ID          | ❌             |
| POST   | `/api/users`      | Create new user(s)      | ✅ JSON array  |
| PUT    | `/api/users/{id}` | Update existing user    | ✅ JSON object |
| DELETE | `/api/users/{id}` | Delete user by ID       | ❌             |
| GET    | `/api/users/page` | Paginated list of users | ❌             |

## 🧪 Testing
| Type        | Tool    | Description             |
| ----------- | ------- | ----------------------- |
| Unit Tests  | JUnit 5 | Method-level validation |
| Integration | MockMvc | End-to-end API test     |

# Run all tests
mvn test

## ✍️ Author
Made by Kashish Choudhary

