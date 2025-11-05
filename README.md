USE hospital;

CREATE TABLE admin (
  Ad_ID INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(255),
  Email VARCHAR(255) UNIQUE,
  Password VARCHAR(255)
);

INSERT INTO admin(Name, Email, Password) VALUES
('AdminSuperUser', 'admin@hospital.com', 'admin123');

CREATE TABLE speclization (
  Sp_Id INT PRIMARY KEY AUTO_INCREMENT,
  Sp_Name VARCHAR(255)
);
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

INSERT INTO patient (Name, DOB, Age, Gender, Blood_Group, Mobile_No, Email, Address, Password) VALUES
('Aarav Sharma',        '1985-07-14', 40, 'Male',   'O+',  '9810012345',   'aarav.sharma1@example.com',   'No.12, MG Road, Delhi',                'pass123'),
('Priya Patel',         '1992-11-30', 32, 'Female', 'A+',  '9123456780',   'priya.patel@example.com',     'Flat 5B, Jubilee Hills, Hyderabad',     'pass123'),
('Rahul Verma',         '2000-03-05', 25, 'Male',   'B+',  '9876543210',   'rahul.verma@example.com',     'Sector 22, Chandigarh',                 'pass123'),
('Sunita Rao',          '1978-12-20', 46, 'Female', 'AB+', '9988776655',   'sunita.rao@example.com',      'R.K. Puram, New Delhi',                 'pass123'),
('Manoj Kumar',         '1965-01-10', 60, 'Male',   'O-',  '9660011223',   'manoj.kumar@example.com',     'Gandhi Nagar, Jaipur',                  'pass123'),
('Ananya Singh',        '2010-08-25', 15, 'Female', 'A-',  '7050012345',   'ananya.singh@example.com',    'Sector 10, Vashi, Navi Mumbai',         'pass123'),
('Vikram Joshi',        '1998-05-17', 27, 'Male',   'B-',  '8877665544',   'vikram.joshi@example.com',    'Baner, Pune',                           'pass123'),
('Kamala Devi',         '1955-04-02', 70, 'Female', 'O+',  '9412345678',   'kamala.devi@example.com',     'Mylapore, Chennai',                     'pass123'),
('Karan Mehta',         '1989-09-09', 36, 'Male',   'A+',  '7890123456',   'karan.mehta@example.com',     'Andheri West, Mumbai',                  'pass123'),
('Nisha Gupta',         '1995-02-28', 30, 'Female', 'B+',  '8123456789',   'nisha.gupta@example.com',     'Kalkaji, New Delhi',                    'pass123'),
('Isha Nair',           '2003-06-15', 22, 'Female', 'AB-', '7634567890',   'isha.nair@example.com',       'Fort Kochi, Kochi',                     'pass123'),
('Ramesh Pillai',       '1970-10-03', 55, 'Male',   'O+',  '9445566778',   'ramesh.pillai@example.com',   'Thiruvananthapuram, Kerala',           'pass123'),
('Meera Iyer',          '1982-01-22', 43, 'Female', 'A+',  '9345678123',   'meera.iyer@example.com',      'T. Nagar, Chennai',                     'pass123'),
('Aditya Kapoor',       '1999-12-31', 25, 'Male',   'B+',  '9988001122',   'aditya.kapoor@example.com',   'Gurgaon Sector 14, Haryana',            'pass123'),
('Raghav Singh',        '1948-11-05', 76, 'Male',   'AB+', '9090909090',   'raghav.singh@example.com',    'Civil Lines, Lucknow',                  'pass123'),
('Tanya Bhattacharya',  '2008-04-18', 17, 'Female', 'O-',  '7203456789',   'tanya.bhatta@example.com',    'Salt Lake, Kolkata',                    'pass123'),
('Deepak Chawla',       '1994-07-07', 31, 'Male',   'A+',  '7700112233',   'deepak.chawla@example.com',   'Model Town, Delhi',                     'pass123'),
('Shreya Sharma',       '1987-03-30', 38, 'Female', 'B-',  '9887766554',   'shreya.sharma@example.com',   'Kothrud, Pune',                         'pass123'),
('Suresh N',            '1960-06-12', 65, 'Male',   'O+',  '9765432109',   'suresh.n@example.com',        'Whitefield, Bangalore',                 'pass123'),
('Aarohi Verma',        '2015-09-01', 10, 'Female', 'A+',  '7012345678',   'aarohi.verma@example.com',    'Hadapsar, Pune',                        'pass123');


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

INSERT INTO labtech (Lb_name, Mobile_no, Email_id, Gender, Age, Experience, Password) VALUES
('Rohan Khanna',        '9876543201', 'rohan.khanna@labmail.com',        'Male',   29, 5,  'pass123'),
('Sneha Deshmukh',      '9123456712', 'sneha.deshmukh@labmail.com',      'Female', 26, 3,  'pass123'),
('Manish Patel',        '9988776611', 'manish.patel@labmail.com',        'Male',   35, 10, 'pass123'),
('Kavita Iyer',         '9090901234', 'kavita.iyer@labmail.com',         'Female', 31, 7,  'pass123'),
('Arjun Reddy',         '9753124680', 'arjun.reddy@labmail.com',         'Male',   28, 4,  'pass123'),
('Neha Kulkarni',       '7766554433', 'neha.kulkarni@labmail.com',       'Female', 30, 6,  'pass123'),
('Sachin Chatterjee',   '8899776655', 'sachin.chatterjee@labmail.com',   'Male',   40, 15, 'pass123'),
('Aditi Sharma',        '9812345078', 'aditi.sharma@labmail.com',        'Female', 24, 2,  'pass123'),
('Vikram Soni',         '7012345698', 'vikram.soni@labmail.com',         'Male',   33, 9,  'pass123'),
('Pooja Jain',          '8866442211', 'pooja.jain@labmail.com',          'Female', 27, 4,  'pass123');

CREATE TABLE pharmacist (
  Ph_ID INT PRIMARY KEY AUTO_INCREMENT,
  Ph_name VARCHAR(255),
  Mobile_no VARCHAR(255),
  Email_id VARCHAR(255) UNIQUE,
  Gender VARCHAR(255),
  Age INT,
  Experience INT,
  Password VARCHAR(255)  
);

INSERT INTO pharmacist (Ph_name, Mobile_no, Email_id, Gender, Age, Experience, Password) VALUES
('Ritesh Malhotra',     '9876501234', 'ritesh.malhotra@pharmahub.com',      'Male',   34,  8,  'pass123'),
('Anjali Saxena',       '9123409876', 'anjali.saxena@pharmahub.com',        'Female', 29,  5,  'pass123'),
('Harish Bhandari',     '9988701122', 'harish.bhandari@pharmahub.com',      'Male',   41, 15,  'pass123'),
('Divya Narang',        '9090123456', 'divya.narang@pharmahub.com',         'Female', 32,  7,  'pass123'),
('Sanjay Kulkarni',     '9753100246', 'sanjay.kulkarni@pharmahub.com',      'Male',   37, 11,  'pass123'),
('Priyanka Shetty',     '7766002233', 'priyanka.shetty@pharmahub.com',      'Female', 27,  3,  'pass123'),
('Vivek Agarwal',       '8899005566', 'vivek.agarwal@pharmahub.com',        'Male',   30,  6,  'pass123'),
('Megha Joshi',         '9812301144', 'megha.joshi@pharmahub.com',          'Female', 25,  2,  'pass123'),
('Amitabh Suresh',      '7012349087', 'amitabh.suresh@pharmahub.com',       'Male',   45, 20,  'pass123'),
('Tanvi Chawla',        '8866113344', 'tanvi.chawla@pharmahub.com',         'Female', 31,  8,  'pass123');

CREATE TABLE medicine_inventory (
  med_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  med_name VARCHAR(255) NOT NULL UNIQUE,
  med_quantity INT NOT NULL DEFAULT 0,
  med_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT chk_med_quantity_nonneg CHECK (med_quantity >= 0),
  CONSTRAINT chk_med_price_nonneg CHECK (med_price >= 0)
);

INSERT INTO medicine_inventory (med_name, med_quantity, med_price) VALUES
('Paracetamol-500mg', 250, 85.00),
('Ibuprofen-400mg', 320, 120.50),
('Amoxicillin-250mg', 150, 210.00),
('Cetrizine-10mg', 400, 75.00),
('Azithromycin-500mg', 180, 350.00),
('Dolo-650mg', 300, 95.00),
('Pantoprazole-40mg', 220, 130.00),
('Metformin-500mg', 500, 60.00),
('Atorvastatin-20mg', 140, 275.00),
('Levocetirizine-5mg', 380, 55.00);

CREATE TABLE `medicine_order` (
  `or_id` INT NOT NULL AUTO_INCREMENT,
  `p_id` INT NOT NULL,
  `dr_id` INT NOT NULL,
  `total_price` DECIMAL(10, 2) NOT NULL,
  `status` VARCHAR(50) NOT NULL DEFAULT 'pending',
  `order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`or_id`),
  KEY `fk_order_patient` (`p_id`),
  KEY `fk_order_doctor` (`dr_id`),
  CONSTRAINT `fk_order_patient` FOREIGN KEY (`p_id`) REFERENCES `patient` (`P_ID`),
  CONSTRAINT `fk_order_doctor` FOREIGN KEY (`dr_id`) REFERENCES `doctor` (`DR_ID`)
);

CREATE TABLE `medicine_order_item` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `or_id` INT NOT NULL,
  `med_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `price_per_item` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_item_order` (`or_id`),
  KEY `fk_item_medicine` (`med_id`),
  CONSTRAINT `fk_item_order` FOREIGN KEY (`or_id`) REFERENCES `medicine_order` (`or_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_item_medicine` FOREIGN KEY (`med_id`) REFERENCES `medicine_inventory` (`med_id`)
);

CREATE TABLE labtest (
    test_id INT PRIMARY KEY AUTO_INCREMENT,
    test_name VARCHAR(100) NOT NULL,
    test_fee DECIMAL(10,2) NOT NULL
);

INSERT INTO labtest (test_name, test_fee) VALUES
('ECG', 600.00),
('Echocardiogram', 2500.00),
('Lipid Profile', 700.00),
('Liver Function Test', 750.00),
('Kidney Function Test', 700.00),
('Thyroid Profile', 800.00),
('HbA1c', 650.00),
('Vitamin D Test', 1200.00),
('Calcium Test', 500.00),
('Iron Studies', 900.00),
('Blood Sugar Fasting', 300.00),
('Blood Sugar Postprandial', 350.00),
('Serum Electrolytes', 600.00),
('Complete Blood Count', 400.00),
('Urine Culture', 800.00),
('Stool Test', 500.00),
('CRP Test', 900.00),
('ESR Test', 400.00),
('Allergy Test', 1500.00),
('HIV Test', 700.00),
('Hepatitis B Test', 800.00),
('Hepatitis C Test', 900.00),
('Pregnancy Test', 300.00),
('Bone Density Test', 2500.00),
('Chest CT Scan', 4000.00);

CREATE TABLE LabAppointment (
    Appointment_ID INT PRIMARY KEY AUTO_INCREMENT,
    P_ID INT NOT NULL,
    Test_ID INT NOT NULL,
    DR_ID INT NOT NULL,
    Lb_ID INT,
    appointment_date DATE,
    status VARCHAR(50) DEFAULT 'Pending',
    remarks VARCHAR(255),

    -- Foreign Keys
    CONSTRAINT fk_labapp_patient
        FOREIGN KEY (P_ID)
        REFERENCES patient(P_ID)
        ON DELETE CASCADE,

    CONSTRAINT fk_labapp_test
        FOREIGN KEY (Test_ID)
        REFERENCES labtest(test_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_labapp_doctor
        FOREIGN KEY (DR_ID)
        REFERENCES doctor(DR_ID)
        ON DELETE CASCADE,

    CONSTRAINT fk_labapp_labtech
        FOREIGN KEY (Lb_ID)
        REFERENCES labtech(Lb_ID)
        ON DELETE SET NULL
);

CREATE TABLE bill_order (
  bill_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT NOT NULL,
  type VARCHAR(10) NOT NULL, -- "doc", "lab", "med"
  item_id INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  payment_status VARCHAR(10) NOT NULL DEFAULT 'pending', -- "pending" or "paid"
  billing_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (patient_id) REFERENCES patient(P_ID),
  -- Add an index to prevent duplicates, complementing the service-layer check
  UNIQUE KEY uk_type_item (type, item_id) 
);



