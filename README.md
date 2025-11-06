# Hospital Management System (HMS) Backend

This repository contains the backend server for a comprehensive Hospital Management System (HMS). It is built using **Spring Boot** and **Java**, providing a complete set of RESTful APIs to manage patients, doctors, appointments, prescriptions, inventory, billing, and more.

The system is designed with a service-oriented architecture, using **JDBC (JdbcTemplate)** for database operations and **Spring Security** for password management.

---

## ✨ Features

This backend supports multiple user roles and a wide range of hospital workflows:

* **Multi-Role Authentication**: Secure, role-based login for Admins, Doctors, Patients, Lab Technicians, and Pharmacists.
* **Patient Management**: Full CRUD operations for patient records, including search by name, email, and blood group. Patients can also change their own password.
* **Staff Management**: Full CRUD operations for Doctors, Lab Technicians, and Pharmacists, including search capabilities.
* **Appointment Scheduling**: Manage both doctor appointments and lab test appointments.
* **Email Notifications**: Automatically sends an email to the patient when a doctor schedules their appointment.
* **Medical Records**: Create and manage patient prescriptions, with the ability to retrieve them by patient, doctor, or appointment.
* **Pharmacy & Inventory**: Full management of medicine inventory, including stock control (`increase`/`decrease`).
* **Medicine Orders**: A complete system for creating and processing medicine orders. Stock is automatically deducted from inventory when an order is marked "completed".
* **Laboratory Management**: Manage available lab tests and their fees and track lab appointments from request to completion.
* **Automated Billing**: Bills are automatically generated in a "pending" state when a doctor appointment, lab appointment, or medicine order is marked as "completed". The system prevents duplicate bills for the same item.
* **Billing Management**: API endpoints to view all bills or filter by patient, and to update payment status from "pending" to "paid".
* **Master Data**: Manage master lists like doctor specializations.
* **Database Seeding**: Automatically populates the database with sample data (doctors, patients, staff) on the first run if the database is empty, making it easy to test.
* **Global Exception Handling**: Provides structured JSON error responses for common issues like `ResourceNotFoundException` or `BusinessRuleException`.

---

## 🛠 Technology Stack

* **Framework**: Spring Boot 3.4.4
* **Language**: Java 17
* **Database**: MySQL
* **Data Access**: Spring Data JDBC (`JdbcTemplate`)
* **Security**: Spring Security (for `BCryptPasswordEncoder`)
* **Email**: Spring Boot Mail Starter
* **Build**: Apache Maven

---

## 🗄 Database Schema

The database schema is defined in `Tablecreation.sql`. Key tables include:

* **User Tables**: `admin`, `doctor`, `patient`, `labtech`, `pharmacist`
* **Master Data**: `speclization`, `labtest`, `medicine_inventory`
* **Transactional Tables**: `appointment` (for doctors), `labappointment`, `prescription`, `medicine_order`, `medicine_order_item`
* **Billing Table**: `bill_order` (links to `appointment`, `labappointment`, or `medicine_order` via `item_id` and `type`)

---

## 🚀 Getting Started

Follow these steps to get the application running locally.

### 1. Prerequisites

* **Java 17** or higher
* **MySQL Server**
* **Maven**

### 2. Database Setup

1.  Start your local MySQL server.
2.  Create a new database named `hospital`.
    ```sql
    CREATE DATABASE hospital;
    ```
3.  Connect to the new database and execute the entire `Tablecreation.sql` script. This will create all tables and populate master data (specializations, lab tests, medicine inventory) and the default admin user.

### 3. Configure Application

1.  Open the `src/main/resources/application.properties` file.
2.  Update the database credentials to match your local setup:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hospital
    spring.datasource.username=YOUR_MYSQL_USERNAME
    spring.datasource.password=YOUR_MYSQL_PASSWORD
    ```
3.  (Optional) To enable email notifications, configure your email provider settings:
    ```properties
    spring.mail.host=smtp.example.com
    spring.mail.port=587
    spring.mail.username=your-email@example.com
    spring.mail.password=your-email-password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```

### 4. Build and Run

You can run the application using the Maven wrapper or directly from your IDE.

**Using Maven Wrapper:**

```bash
# On Linux/macOS
./mvnw spring-boot:run

# On Windows
./mvnw.cmd spring-boot:run
