<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hostel Management System - README</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2, h3 {
            color: #333;
        }
        pre {
            background: #222;
            color: #fff;
            padding: 10px;
            border-radius: 5px;
            overflow-x: auto;
        }
        code {
            font-family: monospace;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Hostel Management System</h1>
        <p>A Java Swing-based Hostel Management System with MySQL integration.</p>

        <h2>Project Structure</h2>
        <pre><code>HostelManagementSystem/
│── src/
│   ├── SignInSignUpPage.java
│   ├── HostelManagementSystem.java
│   ├── background.png
│── README.html
│── database.sql (for table structure)</code></pre>

        <h2>Database Setup</h2>
        <p>Run the following SQL script to create the required database:</p>
        <pre><code>CREATE DATABASE hostel_management;
USE hostel_management;
CREATE TABLE students1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(10) NOT NULL,
    hostel VARCHAR(50),
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);</code></pre>

        <h2>GitHub Setup</h2>
        <p>Follow these commands to initialize and push the project to GitHub:</p>
        <pre><code>git init
git remote add origin https://github.com/yourusername/HostelManagementSystem.git
git add .
git commit -m "Initial commit"
git pull origin main --rebase

git push -u origin main</code></pre>

        <h2>How to Run</h2>
        <p>Compile and run the Java files:</p>
        <pre><code>javac -cp .;mysql-connector-java.jar src/SignInSignUpPage.java
java -cp .;mysql-connector-java.jar src.SignInSignUpPage</code></pre>

        <h2>Contributing</h2>
        <ul>
            <li>Fork the repository</li>
            <li>Clone it: <code>git clone https://github.com/yourusername/HostelManagementSystem.git</code></li>
            <li>Create a branch: <code>git checkout -b feature-branch</code></li>
            <li>Commit changes: <code>git commit -m "Your message"</code></li>
            <li>Push to GitHub: <code>git push origin feature-branch</code></li>
            <li>Create a Pull Request</li>
        </ul>

        <h2>License</h2>
        <p>This project is licensed under the MIT License.</p>
    </div>
</body>
</html>
