package com.hospital.backend.repository;

import com.hospital.backend.entity.MedicineOrder;
import com.hospital.backend.entity.MedicineOrderItem;

import java.util.List;
import java.util.Optional;

public interface MedicineOrderRepository {

    MedicineOrder saveOrder(MedicineOrder order);

    Optional<MedicineOrder> findById(int id);

    List<MedicineOrder> findAll();

    List<MedicineOrder> findByPatientId(int pId);

    List<MedicineOrder> findByDoctorId(int drId);

    List<MedicineOrder> findByStatus(String status);

    MedicineOrder updateStatus(int id, String status);

    void deleteById(int id);

    boolean existsById(int id);

    List<MedicineOrderItem> findItemsByOrderId(int orId);
}

