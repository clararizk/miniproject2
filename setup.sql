CREATE DATABASE IF NOT EXISTS miniproject3_db;
USE miniproject3_db;

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS employee;

CREATE TABLE accounts (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          password VARCHAR(100) NOT NULL
);

CREATE TABLE client (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100),
                        phone VARCHAR(30),
                        address VARCHAR(200)
);

CREATE TABLE employee (
                          id INT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100),
                          department VARCHAR(100),
                          salary DOUBLE
);

INSERT INTO accounts (username, password)
VALUES ('admin', '1234');

INSERT INTO employee (id, name, email, department, salary)
VALUES
    (1, 'Ali Hassan', 'ali@gmail.com', 'HR', 1200.0),
    (2, 'Sara Nader', 'sara@gmail.com', 'IT', 1500.0);

INSERT INTO client (name, email, phone, address)
VALUES
    ('Maya Ali', 'maya@gmail.com', '12345678', 'Beirut'),
    ('Omar Khaled', 'omar@gmail.com', '87654321', 'Tripoli');