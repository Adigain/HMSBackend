package com.hospital.backend.repository;

import com.hospital.backend.entity.LabAppointment;
import java.util.List;
import java.util.Optional;

public interface LabAppointmentRepository {
    LabAppointment saveLabAppointment(LabAppointment labAppointment);
    Optional<LabAppointment> getLabAppointmentById(int id);
    List<LabAppointment> getAllLabAppointments();
    void deleteLabAppointment(int id);
    List<LabAppointment> searchLabAppointmentsByPatientName(String name);
    boolean existsById(int id);
    LabAppointment updateLabAppointment(LabAppointment labAppointment);
}
