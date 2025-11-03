package com.hospital.backend.service;

import com.hospital.backend.entity.MedicineOrder;

import java.util.List;
import java.util.Optional;

public interface MedicineOrderService {

    MedicineOrder createOrder(MedicineOrder order);

    Optional<MedicineOrder> getOrderById(int id);

    List<MedicineOrder> getAllOrders();

    List<MedicineOrder> getOrdersByPatient(int pId);

    List<MedicineOrder> getOrdersByDoctor(int drId);

    List<MedicineOrder> getOrdersByStatus(String status);

    MedicineOrder updateOrderStatus(int id, String status);

    void deleteOrder(int id);
}

