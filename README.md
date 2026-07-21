# 🏟️ TurfHub – Turf Booking & Management System

A full-stack web application that enables users to browse, search, and book sports turfs while allowing turf owners to manage their turfs, bookings, and revenue through a dedicated owner dashboard.

---

## 📌 Project Overview

**Technologies Used:** Java, Servlets, JDBC, MySQL, React.js, JavaScript, Tailwind CSS

- Developed a full-stack turf booking platform with **role-based authentication**, enabling users to browse, search, and book sports turfs while allowing owners to manage turfs and bookings.
- Built a **Servlet-based backend** using Java Servlets, JDBC, and MySQL to implement CRUD operations, booking management, and database connectivity.
- Implemented **role-based access control** and a responsive React.js interface featuring slot booking, booking history, owner dashboard, profile management, and revenue tracking.
- Designed the application using a **layered architecture (React UI → Controller → DTO → DAO → MySQL Database)** to improve maintainability, modularity, and separation of concerns.

---

# 🚀 Features

## 👤 User Module

- User Registration
- User Login
- Browse Available Turfs
- Search Turfs
- View Turf Details
- Select Date & Time Slot
- Book Turf
- Booking Confirmation
- View Booking History
- Manage User Profile
- Change Password

---

## 🏟️ Owner Module

- Owner Login
- Owner Dashboard
- View Revenue Summary
- View Booking Statistics
- Add New Turf
- View My Turfs
- Edit Turf Details
- Manage Owner Profile
- View Customer Bookings

---

## 🔐 Authentication & Authorization

- Role-Based Authentication
- Credential-Based Login
- Protected Routes
- Separate User & Owner Dashboards
- Session-Based Authentication

---

# 🛠️ Tech Stack

## Frontend

- React.js
- React Router DOM
- JavaScript (ES6)
- HTML5
- CSS3
- Tailwind CSS
- Axios
- Lucide React

## Backend

- Java
- Java Servlets
- JDBC
- MySQL
- Gson

## Development Tools

- Eclipse IDE
- Visual Studio Code
- Apache Tomcat
- MySQL Workbench
- Git
- GitHub
- Postman

---

# 🏗️ System Architecture

```
                React.js Frontend
                       │
                       ▼
             Java Servlet Controllers
                       │
                       ▼
                   DTO Layer
                       │
                       ▼
                   DAO Layer
                       │
                       ▼
                MySQL Database
```

---

# 📂 Project Structure

```
TurfHub
│
├── frontend
│   ├── src
│   │   ├── components
│   │   │   ├── owner
│   │   │   ├── Navbar
│   │   │   ├── Footer
│   │   │   ├── Hero
│   │   │   ├── TurfCard
│   │   │   └── ...
│   │   │
│   │   ├── pages
│   │   │   ├── Home
│   │   │   ├── Login
│   │   │   ├── Signup
│   │   │   ├── Booking
│   │   │   ├── Profile
│   │   │   ├── MyBookings
│   │   │   └── owner
│   │   │
│   │   ├── services
│   │   ├── routes
│   │   └── App.jsx
│
├── backend
│   ├── controller
│   ├── dao
│   ├── dto
│   ├── database
│   ├── utility
│   └── images
│
└── database
    └── MySQL
```

---

# 🗄️ Database Tables

- Users
- Turfs
- Turf_Images
- Turf_Sports
- Bookings

---

# 🔄 Application Workflow

## User Flow

```
Register/Login
      │
      ▼
Browse Turfs
      │
      ▼
Search Turf
      │
      ▼
View Turf Details
      │
      ▼
Choose Date & Time Slot
      │
      ▼
Book Turf
      │
      ▼
Booking Confirmation
      │
      ▼
Booking History
```

---

## Owner Flow

```
Login
   │
   ▼
Dashboard
   │
   ▼
Manage Turfs
   │
   ▼
View Bookings
   │
   ▼
Revenue Dashboard
   │
   ▼
Profile Management
```

---

# 📸 Application Screens

- Home
- Login
- Registration
- Turf Listing
- Turf Details
- Booking Page
- Booking History
- User Profile
- Owner Dashboard
- My Turfs
- Add Turf
- Owner Bookings
- Owner Profile

---

# ⚙️ Installation Guide

## Clone Repository

```bash
git clone https://github.com/your-username/TurfHub.git
```

---

## Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Application runs at

```
http://localhost:5173
```

---

## Backend Setup

1. Import the project into Eclipse IDE.
2. Configure Apache Tomcat.
3. Create the MySQL database.
4. Update database credentials.
5. Run the project.

Backend URL

```
http://localhost:8080
```

---

# 🗃️ Database Configuration

Update the database credentials inside:

```
DBConnection.java
```

```java
URL = jdbc:mysql://localhost:3306/turf_booking
USERNAME = root
PASSWORD = your_password
```

---

# 🔐 Authentication Flow

```
Login
   │
   ▼
Verify Credentials
   │
   ▼
Check Role
   │
   ├──────────────► USER
   │                   │
   │                   ▼
   │               User Dashboard
   │
   └──────────────► OWNER
                       │
                       ▼
                Owner Dashboard
```

---

# ✨ Key Highlights

- Full-Stack Web Application
- Layered Architecture
- Role-Based Authentication
- Protected Routes
- CRUD Operations
- Slot-Based Booking System
- Image Upload
- Booking Management
- Revenue Dashboard
- Responsive UI
- MySQL Database Integration

---

# 🚀 Future Enhancements

- Admin Dashboard
- Online Payment Gateway
- Reviews & Ratings
- Notifications
- City-Based Filtering
- Advanced Search
- Booking Analytics
- AI-Based Turf Recommendations
- Mobile Application

---

# 👨‍💻 Author

**Momin Mohammed Rehan**

Java Full Stack Developer

**Tech Stack:** Java • Servlets • JDBC • MySQL • React.js • Tailwind CSS

---

## ⭐ Support

If you found this project useful, consider giving it a **⭐ Star** on GitHub.
