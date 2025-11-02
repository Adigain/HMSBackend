// package com.hospital.backend.controller;

// import com.hospital.backend.entity.LabAppointment;
// import com.hospital.backend.service.LabAppointmentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/labappointments")
// @CrossOrigin
// public class LabAppointmentController {

//     @Autowired
//     private LabAppointmentService service;

//     @PostMapping
//     public LabAppointment createAppointment(@RequestBody LabAppointment appointment) {
//         System.out.println("Received Request: " + appointment);
//         return service.saveLabAppointment(appointment);
//     }

//     @GetMapping
//     public List<LabAppointment> getAll() {
//         return service.getAllLabAppointments();
//     }

//     @GetMapping("/{id}")
//     public LabAppointment getById(@PathVariable int id) {
//         return service.getLabAppointmentById(id).orElse(null);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable int id) {
//         service.deleteLabAppointment(id);
//     }

//     @PutMapping
//     public LabAppointment update(@RequestBody LabAppointment appointment) {
//         return service.updateLabAppointment(appointment);
//     }
// }
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
}
