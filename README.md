
# Hostel Management System

## 📌 Project Description
This is a **Java Swing-based GUI application** for a **Hostel Management System**. It allows users to **Sign In** and **Sign Up** with credentials stored in a MySQL database. The application features a splash screen, an intuitive UI, and a background image (`background.png`).

## 🛠️ Tech Stack
- **Java (Swing)** - GUI for Sign In & Sign Up
- **JDBC (MySQL)** - Database connection for user authentication
- **Git & GitHub** - Version control and repository hosting

---
## 🚀 How to Set Up & Run the Project

### **🔹 Prerequisites**
1. Install **Java JDK 8 or later**
2. Install **MySQL** and create a database named `hostel_management`
3. Install **Git**

### **📂 Project Structure**
```
HostelManagementSystem/
│── src/
│   ├── SignInSignUpPage.java
│   ├── HostelManagementSystem.java
│   ├── background.png
│── images/
│   ├── Dashboard.png
│   ├── SignInPage.png
│   ├── SignUpPage.png
│   ├── SplashScreen.png
│── README.md
│── .gitignore
│── database.sql (optional - if database schema is included)
```

---
## 🔧 **Database Setup**
1. Open MySQL and create the database:
   ```sql
   CREATE DATABASE hostel_management;
   USE hostel_management;
   ```
2. Create the tables:

### **1️⃣ Admin Details Table**
```sql
CREATE TABLE admin_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry_type VARCHAR(255),
    details TEXT,
    entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### **2️⃣ In-Out Time Table**
```sql
CREATE TABLE in_out_time (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(255),
    time_type ENUM('In', 'Out'),
    time VARCHAR(255)
);
```

### **3️⃣ Leave Applications Table**
```sql
CREATE TABLE leave_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(id)
);
```

### **4️⃣ Rooms Table**
```sql
CREATE TABLE rooms (
    room_no INT PRIMARY KEY,
    beds VARCHAR(255)
);
```

### **5️⃣ Students Table**
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

### **6️⃣ User Details Table**
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
    gender VARCHAR(255),
    work_place VARCHAR(255)
);
```

### **7️⃣ Users Table**
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

### **8️⃣ Visitors Table**
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

### **9️⃣ Students1 Table (Alternate Users Table)**
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

---
## 🖥️ **Run the Application**
1. **Compile the Java files**
   ```sh
   javac -cp .;mysql-connector-java-8.0.33.jar src/*.java
   ```
2. **Run the application**
   ```sh
   java -cp .;mysql-connector-java-8.0.33.jar src.SignInSignUpPage
   ```

---
## 📸 **GUI Screenshots**
### **Dashboard**
![Dashboard](images/Dashboard.png)

### **Sign In Page**
![Sign In Page](images/SignInPage.png)

### **Sign Up Page**
![Sign Up Page](images/SignUpPage.png)

### **Splash Screen**
![Splash Screen](images/SplashScreen.png)

---
## 🌍 **Upload to GitHub**
### **📌 Step 1: Initialize Git**
```sh
git init
git add .
git commit -m "Initial commit"
```

### **📌 Step 2: Link to GitHub Repository**
1. Create a repository on GitHub (e.g., `HostelManagementSystem`)
2. Run the following commands:
```sh
git branch -M main
git remote add origin https://github.com/your-username/HostelManagementSystem.git
git push -u origin main
```

### **📌 If Push Fails (Fix Remote Conflicts)**
```sh
git pull origin main --rebase
git push -u origin main
```

🔴 **If conflicts persist and you're sure about overwriting remote changes:**
```sh
git push -u origin main --force
```

---
## 💡 **Contributing**
1. Fork the repository
2. Clone it: `git clone https://github.com/your-username/HostelManagementSystem.git`
3. Create a new branch: `git checkout -b feature-branch`
4. Make changes and commit: `git commit -m "Your message"`
5. Push changes: `git push origin feature-branch`
6. Create a Pull Request on GitHub

---
## 📜 **License**
This project is licensed under the **MIT License**.

---
## 📞 **Contact**
For any queries, feel free to reach out via **GitHub Issues**.

---
🚀 **Happy Coding!**

