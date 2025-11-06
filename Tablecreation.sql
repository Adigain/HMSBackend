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



