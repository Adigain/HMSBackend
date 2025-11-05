package com.hospital.backend;

import com.hospital.backend.entity.Doctor;
import com.hospital.backend.entity.Labtech;
import com.hospital.backend.entity.Patient;
import com.hospital.backend.entity.Pharmacist;
import com.hospital.backend.service.DoctorService;
import com.hospital.backend.service.LabtechService;
import com.hospital.backend.service.PatientService;
import com.hospital.backend.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Logger;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(DataSeeder.class.getName());

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private LabtechService labtechService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    private Random random = new Random();

    private static final String[] MALE_FIRST_NAMES = {
            "Aarav", "Vihaan", "Aditya", "Arjun", "Sai", "Reyansh", "Krishna", "Ishaan", "Rohan", "Aryan",
            "Rahul", "Karan", "Siddharth", "Ankit", "Vikram", "Ravi", "Mohan", "Deepak", "Amit", "Raj"
    };

    private static final String[] FEMALE_FIRST_NAMES = {
            "Aanya", "Diya", "Priya", "Riya", "Saanvi", "Anika", "Gauri", "Ishita", "Kavya", "Myra",
            "Pooja", "Sunita", "Meera", "Anjali", "Sneha", "Kiran", "Lata", "Nisha", "Rani", "Shanti"
    };

    private static final String[] LAST_NAMES = {
            "Sharma", "Verma", "Gupta", "Singh", "Patel", "Kumar", "Das", "Jain", "Yadav", "Mehta",
            "Shah", "Reddy", "Nair", "Iyer", "Rao", "Murthy", "Biswas", "Malhotra", "Chopra", "Chavan"
    };

    private String getRandomElement(String[] arr) {
        return arr[random.nextInt(arr.length)];
    }

    private String generateEmail(String firstName, String lastName, int id) {
        return (firstName + "." + lastName + id).toLowerCase() + "@hospital.com";
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Checking for existing data...");

        if (patientService.getAllPatients().isEmpty()) {
            LOGGER.info("No data found. Seeding database...");

            // Seed 20 Patients
            for (int i = 1; i <= 20; i++) {
                Patient p = new Patient();
                String gender = (i % 2 == 0) ? "Female" : "Male";
                String firstName = (gender.equals("Female")) ? getRandomElement(FEMALE_FIRST_NAMES) : getRandomElement(MALE_FIRST_NAMES);
                String lastName = getRandomElement(LAST_NAMES);

                p.setName(firstName + " " + lastName);
                p.setEmail(generateEmail(firstName, lastName, i));
                p.setPassword("passp" + i); 
                p.setDob(LocalDate.now().minusYears(30 + i).minusDays(random.nextInt(365)));
                p.setAge(30 + i);
                p.setGender(gender);
                p.setBloodGroup(i % 4 == 0 ? "A+" : (i % 4 == 1 ? "B+" : (i % 4 == 2 ? "O+" : "AB+")));
                p.setMobileNo("9876543" + String.format("%03d", i));
                p.setAddress(random.nextInt(1000) + " " + getRandomElement(LAST_NAMES) + " Nagar");
                patientService.save(p);
            }
            LOGGER.info("Seeded 20 patients.");

            // Seed 10 Labtechs
            for (int i = 1; i <= 10; i++) {
                Labtech l = new Labtech();
                String gender = (i % 2 == 0) ? "Male" : "Female";
                String firstName = (gender.equals("Female")) ? getRandomElement(FEMALE_FIRST_NAMES) : getRandomElement(MALE_FIRST_NAMES);
                String lastName = getRandomElement(LAST_NAMES);

                l.setLbName(firstName + " " + lastName);
                l.setEmailId(generateEmail(firstName, lastName, i));
                l.setPassword("passl" + i); 
                l.setAge(25 + i);
                l.setGender(gender);
                l.setExperience(i);
                l.setMobileNo("9111111" + String.format("%03d", i));
                labtechService.saveLabtech(l);
            }
            LOGGER.info("Seeded 10 labtechs.");

            // Seed 10 Pharmacists
            for (int i = 1; i <= 10; i++) {
                Pharmacist ph = new Pharmacist();
                String gender = (i % 2 == 0) ? "Female" : "Male";
                String firstName = (gender.equals("Female")) ? getRandomElement(FEMALE_FIRST_NAMES) : getRandomElement(MALE_FIRST_NAMES);
                String lastName = getRandomElement(LAST_NAMES);

                ph.setPhName(firstName + " " + lastName);
                ph.setEmailId(generateEmail(firstName, lastName, i));
                ph.setPassword("passm" + i); 
                ph.setAge(30 + i);
                ph.setGender(gender);
                ph.setExperience(i + 2);
                ph.setMobileNo("9222222" + String.format("%03d", i));
                pharmacistService.savePharmacist(ph);
            }
            LOGGER.info("Seeded 10 pharmacists.");

            // Seed 40 Doctors (2 for each of 20 specializations)
            for (int j = 1; j <= 20; j++) {
                int docId1 = (j * 2) - 1;
                String fName1 = getRandomElement(MALE_FIRST_NAMES);
                String lName1 = getRandomElement(LAST_NAMES);

                Doctor d1 = new Doctor();
                d1.setDrName("Dr. " + fName1 + " " + lName1);
                d1.setEmailId(generateEmail(fName1, lName1, docId1));
                d1.setPassword("passd" + docId1); 
                d1.setSpId(j); 
                d1.setAge(40 + j);
                d1.setGender("Male");
                d1.setExperience(10 + j);
                d1.setMobileNo("9333333" + String.format("%03d", docId1));
                d1.setPicture("default_doc.jpg");
                doctorService.saveDoctor(d1);

                int docId2 = (j * 2);
                String fName2 = getRandomElement(FEMALE_FIRST_NAMES);
                String lName2 = getRandomElement(LAST_NAMES);
                Doctor d2 = new Doctor();
                d2.setDrName("Dr. " + fName2 + " " + lName2);
                d2.setEmailId(generateEmail(fName2, lName2, docId2));
                d2.setPassword("passd" + docId2); 
                d2.setSpId(j); 
                d2.setAge(35 + j);
                d2.setGender("Female");
                d2.setExperience(5 + j);
                d2.setMobileNo("9444444" + String.format("%03d", docId2));
                d2.setPicture("default_doc.jpg");
                doctorService.saveDoctor(d2);
            }
            LOGGER.info("Seeded 40 doctors.");
            LOGGER.info("Database seeding complete.");

        } else {
            LOGGER.info("Data already exists. Skipping seeding.");
        }
    }
}