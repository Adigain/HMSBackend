package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.LabAppointment;
import com.hospital.backend.entity.Labtest;
import com.hospital.backend.exceptions.ResourceNotFoundException;
import com.hospital.backend.repository.LabAppointmentRepository;
import com.hospital.backend.service.BillOrderService; 
import com.hospital.backend.service.LabAppointmentService;
import com.hospital.backend.service.LabtestService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;
import java.util.Optional;

@Service
public class LabAppointmentServiceImpl implements LabAppointmentService {

    @Autowired
    private LabAppointmentRepository repo;

    @Autowired
    private LabtestService labtestService; 

    @Autowired
    private BillOrderService billOrderService; 

    @Override
    public LabAppointment saveLabAppointment(LabAppointment labAppointment) {
        if (labAppointment.getPId() == null)
            throw new IllegalArgumentException("Patient ID (pId) cannot be null");
        return repo.saveLabAppointment(labAppointment);
    }

    @Override
    public Optional<LabAppointment> getLabAppointmentById(int id) {
        return repo.getLabAppointmentById(id);
    }

    @Override
    public List<LabAppointment> getAllLabAppointments() {
        return repo.getAllLabAppointments();
    }

    @Override
    public void deleteLabAppointment(int id) {
        repo.deleteLabAppointment(id);
    }

    @Override
    public List<LabAppointment> searchLabAppointmentsByPatientName(String name) {
        return repo.searchLabAppointmentsByPatientName(name);
    }

    @Override
    public boolean existsById(int id) {
        return repo.existsById(id);
    }

    @Override
    @Transactional 
    public LabAppointment updateLabAppointment(LabAppointment updatedAppointment) {
        int appointmentId = updatedAppointment.getAppointmentId();
        
        LabAppointment existingAppointment = getLabAppointmentById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found: " + appointmentId));

        String oldStatus = existingAppointment.getStatus();
        String newStatus = updatedAppointment.getStatus();

        LabAppointment savedAppointment = repo.updateLabAppointment(updatedAppointment);

        boolean isNowCompleted = "completed".equalsIgnoreCase(newStatus) && !"completed".equalsIgnoreCase(oldStatus);

        if (isNowCompleted) {

            if (!billOrderService.hasBillBeenGenerated(appointmentId, "lab")) {
                try {
                    int testId = savedAppointment.getTestId();
                    Labtest labTest = labtestService.getLabtestById(testId)
                        .orElseThrow(() -> new ResourceNotFoundException("Labtest", "id", testId));
                    
                    double price = labTest.getTestFee();
                    
                    billOrderService.createBillOrder(
                        savedAppointment.getPId(),     
                        "lab",                   
                        savedAppointment.getAppointmentId(), 
                        price                           
                    );

                } catch (Exception e) {                    
                    System.err.println("CRITICAL: Failed to create bill for completed lab appointment " + appointmentId + ". Error: " + e.getMessage());                    
                }
            }
        }

        return savedAppointment;
    }

    @Override
    public List<LabAppointment> getLabAppointmentsByDoctorId(int doctorId) {
        return repo.getLabAppointmentsByDoctorId(doctorId);
    }

    @Override
    public List<LabAppointment> getLabAppointmentsByLabTechId(int labTechId) {
        return repo.getLabAppointmentsByLabTechId(labTechId);
    }

    @Override
    public List<LabAppointment> getLabAppointmentsByPatientId(int patientId) {
        return repo.getLabAppointmentsByPatientId(patientId);
    }

    @Override
    public List<LabAppointment> getAllCompletedAppointments() {
        return repo.getAllCompletedAppointments();
    }

    @Override
    public List<LabAppointment> getAllPastAppointments() {
        return repo.getAllPastAppointments();
    }
    @Override
    public List<LabAppointment> getAllUpcomingOrPendingAppointments() {
        return repo.getAllUpcomingOrPendingAppointments();
    }
}
