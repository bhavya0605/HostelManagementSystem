# Hostel Management System

## Introduction
The **Hostel Management System** is designed to efficiently manage hostel-related operations, including student records, room allocations, visitor logs, and leave applications.

## Features
- Student and Admin Authentication
- Room and Student Management
- Visitor Tracking System
- Leave Application Management
- Entry/Exit Logs for Students

## Project Structure
```
HostelManagementSystem/
│── src/
│   ├── Background.png
│   ├── Main.java
│   ├── HostelManagement.java
│── README.md
│── .gitignore
│── database.sql
```

## Database Schema
The system consists of **nine tables**:

### **1. admin_details**
```sql
CREATE TABLE admin_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry_type VARCHAR(255),
    details TEXT,
    entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### **2. in_out_time**
```sql
CREATE TABLE in_out_time (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(255),
    time_type ENUM('IN', 'OUT'),
    time VARCHAR(255)
);
```

### **3. leave_applications**
```sql
CREATE TABLE leave_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(id)
);
```

### **4. rooms**
```sql
CREATE TABLE rooms (
    room_no INT PRIMARY KEY,
    beds VARCHAR(255)
);
```

### **5. students**
```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    father_name VARCHAR(255),
    mother_name VARCHAR(255),
    dob DATE,
    contact VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(255),
    vehicle VARCHAR(255),
    workplace VARCHAR(255),
    gender ENUM('Male', 'Female', 'Other')
);
```

### **6. students1**
```sql
CREATE TABLE students1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    hostel VARCHAR(255),
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### **7. user_details**
```sql
CREATE TABLE user_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    father_name VARCHAR(255),
    mother_name VARCHAR(255),
    dob DATE,
    contact VARCHAR(255),
    email VARCHAR(255),
    address TEXT,
    vehicle VARCHAR(255),
    workplace VARCHAR(255),
    gender VARCHAR(50),
    work_place VARCHAR(255)
);
```

### **8. users**
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    hostel_name VARCHAR(255) NOT NULL,
    room_number INT NOT NULL
);
```

### **9. visitors**
```sql
CREATE TABLE visitors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    visitor_name VARCHAR(255),
    purpose VARCHAR(255),
    visit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    student_id INT,
    FOREIGN KEY (student_id) REFERENCES students(id)
);
```

---

## How to Set Up the Project Locally
### **1. Clone the Repository**
```sh
git clone https://github.com/bhavya0605/HostelManagementSystem.git
cd HostelManagementSystem
```

### **2. Run the Java Program**
Compile and run your Java program:
```sh
javac src/*.java
java src.Main
```

### **3. Set Up the Database**
1. Start MySQL and create the database:
```sql
CREATE DATABASE hostel_management;
USE hostel_management;
```
2. Copy and run the table creation queries from **database.sql** in MySQL.

---

## Contributing
1. Fork the repository.
2. Clone your forked repository:
```sh
git clone https://github.com/yourusername/HostelManagementSystem.git
cd HostelManagementSystem
```
3. Create a new branch:
```sh
git checkout -b feature-branch
```
4. Make changes and commit:
```sh
git add .
git commit -m "Added new feature"
```
5. Push changes:
```sh
git push origin feature-branch
```
6. Open a **Pull Request** on GitHub.

---

## License
This project is licensed under the MIT License.

