USE hospital;

CREATE TABLE admin (
  Ad_ID INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(255),
  Email VARCHAR(255) UNIQUE,
  Password VARCHAR(255)
);

CREATE TABLE speclization (
  Sp_Id INT PRIMARY KEY AUTO_INCREMENT,
  Sp_Name VARCHAR(255)
);

CREATE TABLE doctor (
  DR_ID INT PRIMARY KEY AUTO_INCREMENT,
  Dr_name VARCHAR(255),
  Mobile_no VARCHAR(255),
  Email_id VARCHAR(255) UNIQUE,
  Gender VARCHAR(255),
  Age INT,
  Experience INT,
  Password VARCHAR(255),
  Sp_Id INT,
  picture VARCHAR(255),
  FOREIGN KEY (Sp_Id) REFERENCES speclization(Sp_Id)
);

CREATE TABLE patient (
  P_ID INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(255),
  DOB DATE,
  Age INT,
  Gender VARCHAR(255),
  Blood_Group VARCHAR(255),
  Mobile_No VARCHAR(255),
  Email VARCHAR(255) UNIQUE,
  Address VARCHAR(255),
  Password VARCHAR(255)
);

CREATE TABLE appointment (
  Ap_ID INT PRIMARY KEY AUTO_INCREMENT,
  P_ID INT,
  DR_ID INT,
  Descript VARCHAR(255),
  cancel_confirm INT,
  appointment_date DATE,
  appointment_time TIME,
  status VARCHAR(255),
  FOREIGN KEY (P_ID) REFERENCES patient(P_ID),
  FOREIGN KEY (DR_ID) REFERENCES doctor(DR_ID)
);

CREATE TABLE prescription (
  Pr_ID INT PRIMARY KEY AUTO_INCREMENT,
  Ap_Id INT,
  P_ID INT,
  medicine VARCHAR(255),
  advice VARCHAR(255),
  remark VARCHAR(255),
  FOREIGN KEY (Ap_Id) REFERENCES appointment(Ap_ID),
  FOREIGN KEY (P_ID) REFERENCES patient(P_ID)
);

CREATE TABLE labtech (
  Lb_ID INT PRIMARY KEY AUTO_INCREMENT,
  Lb_name VARCHAR(255),
  Mobile_no VARCHAR(255),
  Email_id VARCHAR(255) UNIQUE,
  Gender VARCHAR(255),
  Age INT,
  Experience INT,
  Password VARCHAR(255)  
);
select*from patient;
INSERT INTO speclization (sp_id, sp_name) VALUES
(1, 'Cardiology'),
(2, 'Dermatology'),
(3, 'Pediatrics'),
(4, 'Neurology'),
(5, 'Orthopedics'),
(6, 'Gastroenterology'),
(7, 'Pulmonology'),
(8, 'Endocrinology'),
(9, 'Nephrology'),
(10, 'Urology'),
(11, 'General Surgery'),
(12, 'Otolaryngology'),
(13, 'Ophthalmology'),
(14, 'Psychiatry'),
(15, 'Oncology'),
(16, 'Rheumatology'),
(17, 'Hematology'),
(18, 'Obstetrics & Gynecology'),
(19, 'Radiology'),
(20, 'Emergency Medicine');

INSERT INTO doctor 
(Dr_name, Mobile_no, Email_id, Gender, Age, Experience, Password, Sp_Id, picture)
VALUES
-- 1. Cardiology
('Dr. Arjun Mehta', '9876543210', 'arjun.mehta@hospital.com', 'Male', 45, 20, 'pass123', 1, 'arjun.jpg'),
('Dr. Priya Nair', '9876543211', 'priya.nair@hospital.com', 'Female', 39, 14, 'pass123', 1, 'priya.jpg'),

-- 2. Dermatology
('Dr. Rohan Gupta', '9876543212', 'rohan.gupta@hospital.com', 'Male', 38, 10, 'pass123', 2, 'rohan.jpg'),
('Dr. Sneha Patel', '9876543213', 'sneha.patel@hospital.com', 'Female', 42, 15, 'pass123', 2, 'sneha.jpg'),

-- 3. Pediatrics
('Dr. Karan Singh', '9876543214', 'karan.singh@hospital.com', 'Male', 37, 11, 'pass123', 3, 'karan.jpg'),
('Dr. Neha Sharma', '9876543215', 'neha.sharma@hospital.com', 'Female', 34, 8, 'pass123', 3, 'neha.jpg'),

-- 4. Neurology
('Dr. Aditya Verma', '9876543216', 'aditya.verma@hospital.com', 'Male', 50, 25, 'pass123', 4, 'aditya.jpg'),
('Dr. Kavita Rao', '9876543217', 'kavita.rao@hospital.com', 'Female', 44, 19, 'pass123', 4, 'kavita.jpg'),

-- 5. Orthopedics
('Dr. Rakesh Bhat', '9876543218', 'rakesh.bhat@hospital.com', 'Male', 46, 22, 'pass123', 5, 'rakesh.jpg'),
('Dr. Pooja Iyer', '9876543219', 'pooja.iyer@hospital.com', 'Female', 36, 12, 'pass123', 5, 'pooja.jpg'),

-- 6. Gastroenterology
('Dr. Mohan Pillai', '9876543220', 'mohan.pillai@hospital.com', 'Male', 48, 23, 'pass123', 6, 'mohan.jpg'),
('Dr. Ritu Deshmukh', '9876543221', 'ritu.deshmukh@hospital.com', 'Female', 40, 14, 'pass123', 6, 'ritu.jpg'),

-- 7. Pulmonology
('Dr. Vivek Joshi', '9876543222', 'vivek.joshi@hospital.com', 'Male', 43, 17, 'pass123', 7, 'vivek.jpg'),
('Dr. Anjali Kaur', '9876543223', 'anjali.kaur@hospital.com', 'Female', 35, 9, 'pass123', 7, 'anjali.jpg'),

-- 8. Endocrinology
('Dr. Nikhil Reddy', '9876543224', 'nikhil.reddy@hospital.com', 'Male', 41, 15, 'pass123', 8, 'nikhil.jpg'),
('Dr. Meena Sahu', '9876543225', 'meena.sahu@hospital.com', 'Female', 37, 10, 'pass123', 8, 'meena.jpg'),

-- 9. Nephrology
('Dr. Ajay Menon', '9876543226', 'ajay.menon@hospital.com', 'Male', 49, 24, 'pass123', 9, 'ajay.jpg'),
('Dr. Rina Paul', '9876543227', 'rina.paul@hospital.com', 'Female', 38, 11, 'pass123', 9, 'rina.jpg'),

-- 10. Urology
('Dr. Suresh Das', '9876543228', 'suresh.das@hospital.com', 'Male', 47, 21, 'pass123', 10, 'suresh.jpg'),
('Dr. Shalini Bose', '9876543229', 'shalini.bose@hospital.com', 'Female', 39, 13, 'pass123', 10, 'shalini.jpg'),

-- 11. General Surgery
('Dr. Deepak Jain', '9876543230', 'deepak.jain@hospital.com', 'Male', 52, 27, 'pass123', 11, 'deepak.jpg'),
('Dr. Lata Sen', '9876543231', 'lata.sen@hospital.com', 'Female', 45, 19, 'pass123', 11, 'lata.jpg'),

-- 12. Otolaryngology
('Dr. Arvind Mishra', '9876543232', 'arvind.mishra@hospital.com', 'Male', 46, 20, 'pass123', 12, 'arvind.jpg'),
('Dr. Shreya Ghosh', '9876543233', 'shreya.ghosh@hospital.com', 'Female', 33, 7, 'pass123', 12, 'shreya.jpg'),

-- 13. Ophthalmology
('Dr. Rajeev Kapoor', '9876543234', 'rajeev.kapoor@hospital.com', 'Male', 44, 18, 'pass123', 13, 'rajeev.jpg'),
('Dr. Isha Khanna', '9876543235', 'isha.khanna@hospital.com', 'Female', 36, 10, 'pass123', 13, 'isha.jpg'),

-- 14. Psychiatry
('Dr. Harish Goyal', '9876543236', 'harish.goyal@hospital.com', 'Male', 42, 15, 'pass123', 14, 'harish.jpg'),
('Dr. Divya Anand', '9876543237', 'divya.anand@hospital.com', 'Female', 34, 8, 'pass123', 14, 'divya.jpg'),

-- 15. Oncology
('Dr. Manish Rathi', '9876543238', 'manish.rathi@hospital.com', 'Male', 48, 23, 'pass123', 15, 'manish.jpg'),
('Dr. Sneha Pillai', '9876543239', 'sneha.pillai@hospital.com', 'Female', 40, 14, 'pass123', 15, 'sneha.jpg'),

-- 16. Rheumatology
('Dr. Ashok Krishnan', '9876543240', 'ashok.krishnan@hospital.com', 'Male', 46, 19, 'pass123', 16, 'ashok.jpg'),
('Dr. Aditi Sen', '9876543241', 'aditi.sen@hospital.com', 'Female', 35, 9, 'pass123', 16, 'aditi.jpg'),

-- 17. Hematology
('Dr. Rohit Malhotra', '9876543242', 'rohit.malhotra@hospital.com', 'Male', 49, 25, 'pass123', 17, 'rohit.jpg'),
('Dr. Sneha Varma', '9876543243', 'sneha.varma@hospital.com', 'Female', 37, 11, 'pass123', 17, 'sneha.jpg'),

-- 18. Obstetrics & Gynecology
('Dr. Pankaj Thakur', '9876543244', 'pankaj.thakur@hospital.com', 'Male', 44, 17, 'pass123', 18, 'pankaj.jpg'),
('Dr. Rachna Das', '9876543245', 'rachna.das@hospital.com', 'Female', 39, 12, 'pass123', 18, 'rachna.jpg'),

-- 19. Radiology
('Dr. Vikram Arora', '9876543246', 'vikram.arora@hospital.com', 'Male', 45, 18, 'pass123', 19, 'vikram.jpg'),
('Dr. Anu Jain', '9876543247', 'anu.jain@hospital.com', 'Female', 38, 10, 'pass123', 19, 'anu.jpg'),

-- 20. Emergency Medicine
('Dr. Alok Roy', '9876543248', 'alok.roy@hospital.com', 'Male', 43, 16, 'pass123', 20, 'alok.jpg'),
('Dr. Nisha Paul', '9876543249', 'nisha.paul@hospital.com', 'Female', 32, 6, 'pass123', 20, 'nisha.jpg');

select*from appointment;
