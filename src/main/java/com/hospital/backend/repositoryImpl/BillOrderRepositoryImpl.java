package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.BillOrder;
import com.hospital.backend.repository.BillOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BillOrderRepositoryImpl implements BillOrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<BillOrder> billOrderRowMapper = (ResultSet rs, int rowNum) -> {
        BillOrder bill = new BillOrder();
        bill.setBillId(rs.getInt("bill_id"));
        bill.setPatientId(rs.getInt("patient_id"));
        bill.setType(rs.getString("type"));
        bill.setItemId(rs.getInt("item_id"));
        bill.setPrice(rs.getDouble("price"));
        bill.setPaymentStatus(rs.getString("payment_status"));
        bill.setBillingDate(rs.getTimestamp("billing_date"));
        return bill;
    };

    @Override
    public BillOrder save(BillOrder billOrder) {
        String sql = "INSERT INTO bill_order (patient_id, type, item_id, price, payment_status, billing_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                billOrder.getPatientId(),
                billOrder.getType(),
                billOrder.getItemId(),
                billOrder.getPrice(),
                billOrder.getPaymentStatus(),
                billOrder.getBillingDate()
        );        
        return billOrder;
    }

    @Override
    public Optional<BillOrder> findById(int billId) {
        String sql = "SELECT * FROM bill_order WHERE bill_id = ?";
        List<BillOrder> bills = jdbcTemplate.query(sql, billOrderRowMapper, billId);
        return bills.isEmpty() ? Optional.empty() : Optional.of(bills.get(0));
    }

    @Override
    public List<BillOrder> findByPatientId(int patientId) {
        String sql = "SELECT * FROM bill_order WHERE patient_id = ?";
        return jdbcTemplate.query(sql, billOrderRowMapper, patientId);
    }

    @Override
    public List<BillOrder> findAll() {
        String sql = "SELECT * FROM bill_order";
        return jdbcTemplate.query(sql, billOrderRowMapper);
    }

    @Override
    public void updatePaymentStatus(int billId, String paymentStatus) {
        String sql = "UPDATE bill_order SET payment_status = ? WHERE bill_id = ?";
        jdbcTemplate.update(sql, paymentStatus, billId);
    }

    @Override
    public boolean existsByItemIdAndType(int itemId, String type) {
        String sql = "SELECT COUNT(*) FROM bill_order WHERE item_id = ? AND type = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, itemId, type);
        return count != null && count > 0;
    }
}
