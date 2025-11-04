package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.BillOrder;
import com.hospital.backend.repository.BillOrderRepository;
import com.hospital.backend.service.BillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BillOrderServiceImpl implements BillOrderService {

    @Autowired
    private BillOrderRepository billOrderRepository;

    @Override
    public BillOrder createBillOrder(int patientId, String type, int itemId, double price) {        
        if (hasBillBeenGenerated(itemId, type)) {            
            System.err.println("Duplicate bill creation attempted for type=" + type + ", itemId=" + itemId);
            return null; 
        }

        BillOrder billOrder = new BillOrder();
        billOrder.setPatientId(patientId);
        billOrder.setType(type);
        billOrder.setItemId(itemId);
        billOrder.setPrice(price);
        billOrder.setPaymentStatus("pending"); 
        billOrder.setBillingDate(new Timestamp(System.currentTimeMillis())); 

        return billOrderRepository.save(billOrder);
    }

    @Override
    public Optional<BillOrder> getBillOrderById(int billId) {
        return billOrderRepository.findById(billId);
    }

    @Override
    public List<BillOrder> getBillOrdersByPatientId(int patientId) {
        return billOrderRepository.findByPatientId(patientId);
    }

    @Override
    public List<BillOrder> getAllBillOrders() {
        return billOrderRepository.findAll();
    }

    @Override
    public BillOrder updatePaymentStatus(int billId, String paymentStatus) {        
        getBillOrderById(billId)
                .orElseThrow(() -> new RuntimeException("BillOrder not found with id: " + billId));

        billOrderRepository.updatePaymentStatus(billId, paymentStatus);
        
        return getBillOrderById(billId).get();
    }

    @Override
    public boolean hasBillBeenGenerated(int itemId, String type) {
        return billOrderRepository.existsByItemIdAndType(itemId, type);
    }
}
