package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.MedicalInventory;
import com.hospital.backend.entity.MedicineOrder;
import com.hospital.backend.entity.MedicineOrderItem;
import com.hospital.backend.exceptions.BusinessRuleException;
import com.hospital.backend.exceptions.ResourceNotFoundException;
import com.hospital.backend.repository.MedicineOrderRepository;
import com.hospital.backend.service.MedicineInventoryService;
import com.hospital.backend.service.MedicineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineOrderServiceImpl implements MedicineOrderService {

    @Autowired
    private MedicineOrderRepository medicineOrderRepository;

    @Autowired
    private MedicineInventoryService medicineInventoryService;

    @Override
    @Transactional
    public MedicineOrder createOrder(MedicineOrder order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new BusinessRuleException("Order must contain at least one item.");
        }

        double calculatedTotalPrice = 0.0;
        
        for (MedicineOrderItem item : order.getItems()) {
            int medId = item.getMedId();
            int requestedQuantity = item.getQuantity();

            if (requestedQuantity <= 0) {
                throw new BusinessRuleException("Item quantity must be positive.");
            }
            
            MedicalInventory medicine = medicineInventoryService.getMedicineById(medId)
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine", "id", medId));
            
            item.setPricePerItem(medicine.getMedPrice());
            calculatedTotalPrice += item.getPricePerItem() * requestedQuantity;
        }

        order.setTotalPrice(calculatedTotalPrice);
        order.setStatus("pending"); 

        MedicineOrder savedOrder = medicineOrderRepository.saveOrder(order);

        return savedOrder;
    }

    @Override
    public Optional<MedicineOrder> getOrderById(int id) {
        return medicineOrderRepository.findById(id);
    }

    @Override
    public List<MedicineOrder> getAllOrders() {
        return medicineOrderRepository.findAll();
    }

    @Override
    public List<MedicineOrder> getOrdersByPatient(int pId) {
        return medicineOrderRepository.findByPatientId(pId);
    }

    @Override
    public List<MedicineOrder> getOrdersByDoctor(int drId) {
        return medicineOrderRepository.findByDoctorId(drId);
    }

    @Override
    public List<MedicineOrder> getOrdersByStatus(String status) {
        return medicineOrderRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public MedicineOrder updateOrderStatus(int id, String status) {        
        if (!"completed".equalsIgnoreCase(status)) {
             return medicineOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicineOrder", "id", id));
        }

        MedicineOrder order = medicineOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicineOrder", "id", id));
        
        if ("completed".equalsIgnoreCase(order.getStatus())) {
            return order;
        }

        for (MedicineOrderItem item : order.getItems()) {
            MedicalInventory medicine = medicineInventoryService.getMedicineById(item.getMedId())
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine", "id", item.getMedId())); // Should not happen if createOrder worked

            if (medicine.getMedQuantity() < item.getQuantity()) {
                throw new BusinessRuleException("Order cannot be processed. Not enough stock for '" +
                        medicine.getMedName() + "'. Requested: " + item.getQuantity() +
                        ", Available: " + medicine.getMedQuantity());
            }
        }

        for (MedicineOrderItem item : order.getItems()) {
            medicineInventoryService.decreaseQuantity(item.getMedId(), item.getQuantity());
        }
        
        return medicineOrderRepository.updateStatus(id, "completed");
    }

    @Override
    @Transactional
    public void deleteOrder(int id) {
        if (!medicineOrderRepository.existsById(id)) {
            throw new ResourceNotFoundException("MedicineOrder", "id", id);
        }
        medicineOrderRepository.deleteById(id);
    }
}

