package com.hospital.backend.service;

import com.hospital.backend.entity.BillOrder;

import java.util.List;
import java.util.Optional;

public interface BillOrderService {

    BillOrder createBillOrder(int patientId, String type, int itemId, double price);

    Optional<BillOrder> getBillOrderById(int billId);

    List<BillOrder> getBillOrdersByPatientId(int patientId);

    List<BillOrder> getAllBillOrders();

    BillOrder updatePaymentStatus(int billId, String paymentStatus);

    boolean hasBillBeenGenerated(int itemId, String type);
}
