package com.hospital.backend.controller;

import com.hospital.backend.entity.MedicineOrder;
import com.hospital.backend.service.MedicineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine-orders")
@CrossOrigin(origins = "*")
public class MedicineOrderController {

    @Autowired
    private MedicineOrderService medicineOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicineOrder createOrder(@RequestBody MedicineOrder order) {
        return medicineOrderService.createOrder(order);
    }

    @GetMapping
    public List<MedicineOrder> getAllOrders() {
        return medicineOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public MedicineOrder getOrderById(@PathVariable int id) {
        return medicineOrderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @GetMapping("/patient/{pId}")
    public List<MedicineOrder> getOrdersByPatient(@PathVariable int pId) {
        return medicineOrderService.getOrdersByPatient(pId);
    }

    @GetMapping("/doctor/{drId}")
    public List<MedicineOrder> getOrdersByDoctor(@PathVariable int drId) {
        return medicineOrderService.getOrdersByDoctor(drId);
    }

    @GetMapping("/status/{status}")
    public List<MedicineOrder> getOrdersByStatus(@PathVariable String status) {
        return medicineOrderService.getOrdersByStatus(status);
    }

    @PutMapping("/{id}/status")
    public MedicineOrder updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        return medicineOrderService.updateOrderStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable int id) {
        medicineOrderService.deleteOrder(id);
    }
}

