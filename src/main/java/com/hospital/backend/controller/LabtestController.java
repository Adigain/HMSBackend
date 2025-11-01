// package com.hospital.backend.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.*;

// import com.hospital.backend.entity.Labtest;
// import com.hospital.backend.service.LabtestService;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping("/api/labtests")
// public class LabtestController {

//     @Autowired
//     private LabtestService labtestService;

//     // Create a new lab test
//     @PostMapping
//     public Labtest createLabtest(@RequestBody Labtest labtest) {
//         return labtestService.saveLabtest(labtest);
//     }

//     // Get all lab tests
//     @GetMapping
//     public List<Labtest> getAllLabtests() {
//         return labtestService.getAllLabtests();
//     }

//     // Get lab test by ID
//     @GetMapping("/{id}")
//     public Labtest getLabtestById(@PathVariable int id) {
//         return labtestService.getLabtestById(id)
//                 .orElseThrow(() -> new RuntimeException("Labtest not found with id: " + id));
//     }

//     // Update existing lab test
//     @PutMapping("/{id}")
//     public Labtest updateLabtest(@PathVariable int id, @RequestBody Labtest labtest) {
//         if (!labtestService.existsById(id)) {
//             throw new RuntimeException("Labtest not found with id: " + id);
//         }

//         labtest.setTestId(id);
//         return labtestService.saveLabtest(labtest);
//     }

//     // Delete a lab test
//     @DeleteMapping("/{id}")
//     @ResponseStatus(HttpStatus.NO_CONTENT)
//     public void deleteLabtest(@PathVariable int id) {
//         labtestService.deleteLabtest(id);
//     }

//     // Search lab tests by name
//     @GetMapping("/search")
//     public List<Labtest> searchLabtestsByName(@RequestParam String name) {
//         return labtestService.searchLabtestsByName(name);
//     }
// }
package com.hospital.backend.controller;

import com.hospital.backend.entity.Labtest;
import com.hospital.backend.service.LabtestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labtests")
@CrossOrigin(origins = "*")
public class LabtestController {

    @Autowired
    private LabtestService labtestService;

    // ✅ Create a new Lab Test
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Labtest createLabtest(@RequestBody Labtest labtest) {
        return labtestService.saveLabtest(labtest);
    }

    // ✅ Get all Lab Tests
    @GetMapping
    public List<Labtest> getAllLabtests() {
        return labtestService.getAllLabtests();
    }

    // ✅ Get Lab Test by ID
    @GetMapping("/{id}")
    public Labtest getLabtestById(@PathVariable int id) {
        return labtestService.getLabtestById(id)
                .orElseThrow(() -> new RuntimeException("Lab Test not found with ID: " + id));
    }

    // ✅ Update Lab Test
    @PutMapping("/{id}")
    public Labtest updateLabtest(@PathVariable int id, @RequestBody Labtest updatedLabtest) {
        if (!labtestService.existsById(id)) {
            throw new RuntimeException("Lab Test not found with ID: " + id);
        }
        updatedLabtest.setTestId(id);
        return labtestService.saveLabtest(updatedLabtest);
    }

    // ✅ Delete Lab Test
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabtest(@PathVariable int id) {
        labtestService.deleteLabtest(id);
    }

    // ✅ Search Lab Tests by Name (partial match)
    @GetMapping("/search")
    public List<Labtest> searchLabtestsByName(@RequestParam String name) {
        return labtestService.searchLabtestsByName(name);
    }
}
