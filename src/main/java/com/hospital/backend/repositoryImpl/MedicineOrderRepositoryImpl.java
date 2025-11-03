package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.MedicineOrder;
import com.hospital.backend.entity.MedicineOrderItem;
import com.hospital.backend.repository.MedicineOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicineOrderRepositoryImpl implements MedicineOrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<MedicineOrder> orderRowMapper = (ResultSet rs, int rowNum) -> {
        MedicineOrder order = new MedicineOrder();
        order.setOrId(rs.getInt("or_id"));
        order.setpId(rs.getInt("p_id"));
        order.setDrId(rs.getInt("dr_id"));
        order.setTotalPrice(rs.getDouble("total_price"));
        order.setStatus(rs.getString("status"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        return order;
    };

    private final RowMapper<MedicineOrderItem> itemRowMapper = (ResultSet rs, int rowNum) -> {
        MedicineOrderItem item = new MedicineOrderItem();
        item.setItemId(rs.getInt("item_id"));
        item.setOrId(rs.getInt("or_id"));
        item.setMedId(rs.getInt("med_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPricePerItem(rs.getDouble("price_per_item"));
        return item;
    };

    @Override
    @Transactional
    public MedicineOrder saveOrder(MedicineOrder order) {        
        String orderSql = "INSERT INTO medicine_order (p_id, dr_id, total_price, status) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getpId());
            ps.setInt(2, order.getDrId());
            ps.setDouble(3, order.getTotalPrice());
            ps.setString(4, order.getStatus()); 
            return ps;
        }, keyHolder);

        int newOrId = keyHolder.getKey().intValue();
        order.setOrId(newOrId);
        
        String itemSql = "INSERT INTO medicine_order_item (or_id, med_id, quantity, price_per_item) VALUES (?, ?, ?, ?)";
        if (order.getItems() != null) {
            for (MedicineOrderItem item : order.getItems()) {
                item.setOrId(newOrId);
                jdbcTemplate.update(itemSql,
                        item.getOrId(),
                        item.getMedId(),
                        item.getQuantity(),
                        item.getPricePerItem());
            }
        }
        return order;
    }

    @Override
    public Optional<MedicineOrder> findById(int id) {
        String sql = "SELECT * FROM medicine_order WHERE or_id = ?";
        List<MedicineOrder> orders = jdbcTemplate.query(sql, orderRowMapper, id);
        if (orders.isEmpty()) {
            return Optional.empty();
        }
        MedicineOrder order = orders.get(0);
        order.setItems(findItemsByOrderId(id));
        return Optional.of(order);
    }

    @Override
    public List<MedicineOrder> findAll() {
        String sql = "SELECT * FROM medicine_order";
        List<MedicineOrder> orders = jdbcTemplate.query(sql, orderRowMapper);
        for (MedicineOrder order : orders) {
            order.setItems(findItemsByOrderId(order.getOrId()));
        }
        return orders;
    }

    @Override
    public List<MedicineOrder> findByPatientId(int pId) {
        String sql = "SELECT * FROM medicine_order WHERE p_id = ?";
        List<MedicineOrder> orders = jdbcTemplate.query(sql, orderRowMapper, pId);
        for (MedicineOrder order : orders) {
            order.setItems(findItemsByOrderId(order.getOrId()));
        }
        return orders;
    }

    @Override
    public List<MedicineOrder> findByDoctorId(int drId) {
        String sql = "SELECT * FROM medicine_order WHERE dr_id = ?";
        List<MedicineOrder> orders = jdbcTemplate.query(sql, orderRowMapper, drId);
        for (MedicineOrder order : orders) {
            order.setItems(findItemsByOrderId(order.getOrId()));
        }
        return orders;
    }

    @Override
    public List<MedicineOrder> findByStatus(String status) {
        String sql = "SELECT * FROM medicine_order WHERE status = ?";
        List<MedicineOrder> orders = jdbcTemplate.query(sql, orderRowMapper, status);
        for (MedicineOrder order : orders) {
            order.setItems(findItemsByOrderId(order.getOrId()));
        }
        return orders;
    }

    @Override
    @Transactional
    public MedicineOrder updateStatus(int id, String status) {
        String sql = "UPDATE medicine_order SET status = ? WHERE or_id = ?";
        jdbcTemplate.update(sql, status, id);
        return findById(id).orElse(null); 
    }

    @Override
    @Transactional
    public void deleteById(int id) {        
        String sql = "DELETE FROM medicine_order WHERE or_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM medicine_order WHERE or_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public List<MedicineOrderItem> findItemsByOrderId(int orId) {
        String sql = "SELECT * FROM medicine_order_item WHERE or_id = ?";
        return jdbcTemplate.query(sql, itemRowMapper, orId);
    }
}

