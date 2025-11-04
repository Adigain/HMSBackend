package com.hospital.backend.controller;

import com.hospital.backend.entity.BillOrder;
import com.hospital.backend.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillOrderController {

    @Autowired
    private BillOrderService billOrderService;

    @GetMapping("/patient/{patientId}")
    public List<BillOrder> getBillOrdersByPatientId(@PathVariable int patientId) {
        return billOrderService.getBillOrdersByPatientId(patientId);
    }

    @GetMapping("/{billId}")
    public BillOrder getBillOrderById(@PathVariable int billId) {
        return billOrderService.getBillOrderById(billId)
                .orElseThrow(() -> new RuntimeException("BillOrder not found with id: " + billId));
    }

    @GetMapping
    public List<BillOrder> getAllBillOrders() {
        return billOrderService.getAllBillOrders();
    }

    @PutMapping("/{billId}/status")
    public BillOrder updatePaymentStatus(@PathVariable int billId, @RequestParam String status) {        
        return billOrderService.updatePaymentStatus(billId, status);
    }

}
