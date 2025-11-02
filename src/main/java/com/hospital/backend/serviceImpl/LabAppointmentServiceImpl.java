
package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.LabAppointment;
import com.hospital.backend.repository.LabAppointmentRepository;
import com.hospital.backend.service.LabAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabAppointmentServiceImpl implements LabAppointmentService {

    @Autowired
    private LabAppointmentRepository repo;

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
    public LabAppointment updateLabAppointment(LabAppointment a) {
        if (!existsById(a.getAppointmentId()))
            throw new RuntimeException("Appointment not found: " + a.getAppointmentId());
        return repo.updateLabAppointment(a);
    }

    // ✅ New methods

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
}
