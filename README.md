TurfHub – Turf Booking & Management System Technologies Used: Java, Servlets, JDBC, MySQL, React.js, JavaScript, Tailwind
•
Developed a full-stack turf booking platform with role-based authentication, enabling users to browse, search, and book sports turfs while allowing owners to manage turfs and bookings.
•
Built a Servlet-based backend using Java Servlets, JDBC, and MySQL to implement CRUD operations, booking management, and database connectivity.
•
Implemented role-based access control, and a responsive React.js interface featuring slot booking, booking history, owner dashboard, profile management, and revenue tracking.
•
Designed the application using a layered architecture (React UI → Controller → DTO → DAO → MySQL Database) to improve code maintainability, modularity, and separation of concerns.

---

# 🚀 Features

## 👤 User Module

- User Registration & Login
- Role-Based Authentication
- Browse Available Turfs
- Search Turfs
- View Turf Details
- Slot-Based Booking
- Booking Confirmation
- My Bookings
- User Profile
- Edit Profile
- Change Password

---

## 🏟️ Owner Module

- Owner Login
- Dashboard
- View Revenue
- View Booking Statistics
- Add Turf
- View My Turfs
- Edit Turf
- Owner Profile
- View Customer Bookings

---

## 🔐 Authentication

- Role-Based Authentication
- Credential-Based Login
- Protected Routes
- Separate User & Owner Dashboards

---

# 🛠️ Technologies Used

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

## Tools

- Eclipse IDE
- VS Code
- Apache Tomcat
- MySQL Workbench
- Git
- GitHub
- Postman (Testing)

---

# 📂 Project Structure

```
TurfHub
│
├── Frontend
│   ├── components
│   │      ├── owner
│   │      ├── Navbar
│   │      ├── Footer
│   │      ├── Hero
│   │      ├── TurfCard
│   │      └── ...
│   │
│   ├── pages
│   │      ├── Home
│   │      ├── Login
│   │      ├── Signup
│   │      ├── Booking
│   │      ├── Profile
│   │      ├── MyBookings
│   │      └── owner
│   │
│   ├── services
│   ├── routes
│   └── App.jsx
│
├── Backend
│   ├── controller
│   ├── dao
│   ├── dto
│   ├── database
│   ├── utility
│   └── images
│
└── Database
    └── MySQL
```

---

# 🗄️ Database Tables

- users
- turfs
- turf_images
- turf_sports
- bookings

---

# 🔄 Application Flow

```
User

↓

Register / Login

↓

Browse Turfs

↓

Search Turf

↓

View Turf Details

↓

Select Date & Slot

↓

Book Turf

↓

Booking Confirmation

↓

My Bookings
```

---

```
Owner

↓

Login

↓

Dashboard

↓

Manage Turfs

↓

View Bookings

↓

Revenue Summary

↓

Profile
```

---

# 📸 Screens

- Home Page
- Login
- Signup
- Turf Listing
- Turf Details
- Booking Page
- My Bookings
- User Profile
- Owner Dashboard
- My Turfs
- Add Turf
- Owner Bookings
- Owner Profile

---

# ⚙️ Installation

## Clone Repository

```bash
git clone https://github.com/your-username/TurfHub.git
```

---

## Frontend

```bash
cd frontend

npm install

npm run dev
```

Runs on

```
http://localhost:5173
```

---

## Backend

Import the project into **Eclipse IDE**

Configure

- Apache Tomcat
- MySQL Database

Run the project on

```
http://localhost:8080
```

---

# 🗃️ Database Configuration

Update database credentials inside

```
DBConnection.java
```

Example

```java
URL = jdbc:mysql://localhost:3306/turf_booking

USERNAME = root

PASSWORD = your_password
```

---

# 🔒 Authentication Flow

```
User Login
      ↓
Role Check
      ↓
USER  → Home

OWNER → Owner Dashboard
```

Protected routes prevent unauthorized access to user and owner pages.

---

# ✨ Key Features

- Full-Stack Architecture
- Responsive UI
- Role-Based Authentication
- Turf Management
- Booking Management
- Revenue Dashboard
- CRUD Operations
- Image Upload
- Protected Routes
- MySQL Database Integration

---

# 🚀 Future Scope

- Admin Panel
- City-Based Turf Filtering
- Advanced Search & Filters
- Reviews & Ratings
- Online Payment Gateway
- Notifications
- Booking Analytics
- AI-Based Turf Recommendations
- Mobile Application

---

# 👨‍💻 Author

**Momin Mohammed Rehan**

- Java Full Stack Developer
- React.js | Java | Servlets | JDBC | MySQL

---

# ⭐ If you like this project

Give this repository a ⭐ on GitHub.
