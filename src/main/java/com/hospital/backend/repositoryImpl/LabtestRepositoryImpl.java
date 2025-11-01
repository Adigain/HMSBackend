package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.Labtest;
import com.hospital.backend.repository.LabtestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class LabtestRepositoryImpl implements LabtestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map result set to Labtest entity
    private final RowMapper<Labtest> labtestRowMapper = new RowMapper<Labtest>() {
        @Override
        public Labtest mapRow(ResultSet rs, int rowNum) throws SQLException {
            Labtest labtest = new Labtest();
            labtest.setTestId(rs.getInt("Test_ID"));
            labtest.setTestName(rs.getString("Test_Name"));
            labtest.setTestFee(rs.getDouble("Test_Fee"));
            return labtest;
        }
    };

    // Save or Update a lab test
    @Override
    public Labtest saveLabtest(Labtest labtest) {
        if (labtest.getTestId() == 0) {
            String sql = "INSERT INTO labtest (Test_Name, Test_Fee) VALUES (?, ?)";
            jdbcTemplate.update(sql, labtest.getTestName(), labtest.getTestFee());
            return labtest;
        } else {
            String sql = "UPDATE labtest SET Test_Name = ?, Test_Fee = ? WHERE Test_ID = ?";
            jdbcTemplate.update(sql, labtest.getTestName(), labtest.getTestFee(), labtest.getTestId());
            return labtest;
        }
    }

    // Explicit update method
    @Override
    public Labtest updateLabtest(Labtest labtest) {
        if (labtest.getTestId() == 0) {
            throw new IllegalArgumentException("Test ID must be provided for update");
        }
        return saveLabtest(labtest);
    }

    // Delete by ID
    @Override
    public void deleteLabtest(int id) {
        String sql = "DELETE FROM labtest WHERE Test_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    // Find by ID
    @Override
    public Optional<Labtest> getLabtestById(int id) {
        String sql = "SELECT * FROM labtest WHERE Test_ID = ?";
        List<Labtest> labtests = jdbcTemplate.query(sql, labtestRowMapper, id);
        return labtests.isEmpty() ? Optional.empty() : Optional.of(labtests.get(0));
    }

    // Get all lab tests
    @Override
    public List<Labtest> getAllLabtests() {
        String sql = "SELECT * FROM labtest";
        return jdbcTemplate.query(sql, labtestRowMapper);
    }

    // Check existence by ID
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM labtest WHERE Test_ID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    // Search lab tests by name (case-insensitive)
    @Override
    public List<Labtest> searchLabtestsByName(String name) {
        String sql = "SELECT * FROM labtest WHERE LOWER(Test_Name) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, labtestRowMapper, "%" + name + "%");
    }
}
