package com.hospital.backend.repository;

import com.hospital.backend.entity.BillOrder;

import java.util.List;
import java.util.Optional;

public interface BillOrderRepository {

    BillOrder save(BillOrder billOrder);

    Optional<BillOrder> findById(int billId);

    List<BillOrder> findByPatientId(int patientId);

    List<BillOrder> findAll();

    void updatePaymentStatus(int billId, String paymentStatus);

    boolean existsByItemIdAndType(int itemId, String type);
}
