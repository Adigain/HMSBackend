package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.MedicalInventory;
import com.hospital.backend.repository.MedicineInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicineInventoryRepositoryImpl implements MedicineInventoryRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public MedicineInventoryRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<MedicalInventory> rowMapper = new RowMapper<MedicalInventory>() {
        @Override
        public MedicalInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
            MedicalInventory m = new MedicalInventory();
            m.setMedId(rs.getInt("med_id"));
            m.setMedName(rs.getString("med_name"));
            m.setMedQuantity(rs.getInt("med_quantity"));
            m.setMedPrice(rs.getDouble("med_price"));
            return m;
        }
    };

    @Override
    public List<MedicalInventory> findAll() {
        String sql = "SELECT med_id, med_name, med_quantity, med_price FROM medicine_inventory ORDER BY med_name";
        return jdbc.query(sql, rowMapper);
    }

    @Override
    public Optional<MedicalInventory> findById(int id) {
        String sql = "SELECT med_id, med_name, med_quantity, med_price FROM medicine_inventory WHERE med_id = ?";
        try {
            MedicalInventory m = jdbc.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(m);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MedicalInventory> findByName(String name) {
        String sql = "SELECT med_id, med_name, med_quantity, med_price FROM medicine_inventory WHERE med_name = ?";
        try {
            MedicalInventory m = jdbc.queryForObject(sql, rowMapper, name);
            return Optional.ofNullable(m);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public MedicalInventory save(MedicalInventory medicine) {        
        String sql = "INSERT INTO medicine_inventory (med_name, med_quantity, med_price) VALUES (?, ?, ?)";        
        jdbc.update(sql, medicine.getMedName(), medicine.getMedQuantity(), medicine.getMedPrice());
        return findByName(medicine.getMedName()).orElse(medicine);
    }

    @Override
    @Transactional
    public MedicalInventory update(MedicalInventory medicine) {
        String sql = "UPDATE medicine_inventory SET med_name = ?, med_quantity = ?, med_price = ? WHERE med_id = ?";
        int updated = jdbc.update(sql,
                medicine.getMedName(),
                medicine.getMedQuantity(),
                medicine.getMedPrice(),
                medicine.getMedId());

        if (updated > 0) {
            return findById(medicine.getMedId()).orElse(medicine);
        } else {            
            return medicine;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        String sql = "DELETE FROM medicine_inventory WHERE med_id = ?";
        int deleted = jdbc.update(sql, id);
        return deleted > 0;
    }

    @Override
    @Transactional
    public boolean decreaseQuantity(int id, int amount) {        
        String sql = "UPDATE medicine_inventory " +
                     "SET med_quantity = med_quantity - ? " +
                     "WHERE med_id = ? AND med_quantity >= ?";

        int rows = jdbc.update(sql, amount, id, amount);
        return rows > 0;
    }

    @Override
    @Transactional
    public boolean increaseQuantity(int id, int amount) {
        String sql = "UPDATE medicine_inventory SET med_quantity = med_quantity + ? WHERE med_id = ?";
        int rows = jdbc.update(sql, amount, id);
        return rows > 0;
}

}
