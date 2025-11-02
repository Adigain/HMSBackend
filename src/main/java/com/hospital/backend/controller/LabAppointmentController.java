
package com.hospital.backend.controller;

import com.hospital.backend.entity.LabAppointment;
import com.hospital.backend.service.LabAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labappointments")
@CrossOrigin(origins = "*")
public class LabAppointmentController {

    @Autowired
    private LabAppointmentService service;

    
    @PostMapping
    public ResponseEntity<LabAppointment> createAppointment(@RequestBody LabAppointment appointment) {
        System.out.println("📩 Received POST Request: " + appointment);
        return ResponseEntity.ok(service.saveLabAppointment(appointment));
    }

    
    @GetMapping
    public ResponseEntity<List<LabAppointment>> getAllAppointments() {
        System.out.println("📤 Received GET All Request");
        List<LabAppointment> list = service.getAllLabAppointments();
        return ResponseEntity.ok(list);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") int id) {
        System.out.println("📤 Received GET by ID Request: " + id);
        return service.getLabAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable("id") int id, @RequestBody LabAppointment appointment) {
        appointment.setAppointmentId(id);
        System.out.println("📩 Received PUT Request: " + appointment);
        return ResponseEntity.ok(service.updateLabAppointment(appointment));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") int id) {
        System.out.println("🗑️ Received DELETE Request: " + id);
        service.deleteLabAppointment(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/search")
    public ResponseEntity<List<LabAppointment>> searchByPatientName(@RequestParam("name") String name) {
        System.out.println("🔍 Searching appointments by patient name: " + name);
        return ResponseEntity.ok(service.searchLabAppointmentsByPatientName(name));
    }

   
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<LabAppointment>> getByDoctorId(@PathVariable("doctorId") int doctorId) {
        System.out.println("📤 Fetching appointments for Doctor ID: " + doctorId);
        return ResponseEntity.ok(service.getLabAppointmentsByDoctorId(doctorId));
    }

    
    @GetMapping("/labtech/{labTechId}")
    public ResponseEntity<List<LabAppointment>> getByLabTechId(@PathVariable("labTechId") int labTechId) {
        System.out.println("📤 Fetching appointments for Lab Technician ID: " + labTechId);
        return ResponseEntity.ok(service.getLabAppointmentsByLabTechId(labTechId));
    }

    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LabAppointment>> getByPatientId(@PathVariable("patientId") int patientId) {
        System.out.println("📤 Fetching appointments for Patient ID: " + patientId);
        return ResponseEntity.ok(service.getLabAppointmentsByPatientId(patientId));
    }

   
    @GetMapping("/completed")
    public ResponseEntity<List<LabAppointment>> getCompletedAppointments() {
        System.out.println("📤 Fetching all completed appointments");
        return ResponseEntity.ok(service.getAllCompletedAppointments());
    }

    
    @GetMapping("/past")
    public ResponseEntity<List<LabAppointment>> getPastAppointments() {
        System.out.println("📤 Fetching all past appointments");
        return ResponseEntity.ok(service.getAllPastAppointments());
    }
}
