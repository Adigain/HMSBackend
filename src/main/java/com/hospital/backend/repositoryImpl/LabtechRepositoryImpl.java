package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.Labtech;
import com.hospital.backend.entity.Patient;
import com.hospital.backend.repository.LabtechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class LabtechRepositoryImpl implements LabtechRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Labtech> labtechRowMapper = new RowMapper<Labtech>() {
        @Override
        public Labtech mapRow(ResultSet rs, int rowNum) throws SQLException {
            Labtech labtech = new Labtech();
            labtech.setLbId(rs.getInt("LB_ID"));
            labtech.setLbName(rs.getString("Lb_name"));
            labtech.setMobileNo(rs.getString("Mobile_no"));
            labtech.setEmailId(rs.getString("Email_id"));
            labtech.setGender(rs.getString("Gender"));
            labtech.setAge(rs.getInt("Age"));
            labtech.setExperience(rs.getInt("Experience"));
            labtech.setPassword(rs.getString("Password"));            
            return labtech;
        }
    };

    @Override
    public Optional<Labtech> getLabtechById(int id) {
        String sql = "SELECT * FROM labtech WHERE LB_ID = ?";
        List<Labtech> labtechs = jdbcTemplate.query(sql, labtechRowMapper, id);
        return labtechs.isEmpty() ? Optional.empty() : Optional.of(labtechs.get(0));
    }

    @Override
    public Optional<Labtech> findByEmail(String email) {
        String sql = "SELECT * FROM labtech WHERE Email_id = ?";
        List<Labtech> labtechs = jdbcTemplate.query(sql, labtechRowMapper, email);
        return labtechs.isEmpty() ? Optional.empty() : Optional.of(labtechs.get(0));
    }



    @Override
    public List<Labtech> searchLabtechsByName(String name) {
        String sql = "SELECT * FROM labtech WHERE LOWER(Lb_name) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, labtechRowMapper, "%" + name + "%");
    }

    @Override
    public Labtech saveLabtech(Labtech labtech) {
        if (labtech.getLbId() == 0) {
            String sql = "INSERT INTO labtech (Lb_name, Mobile_no, Email_id, Gender, Age, Experience, Password) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            jdbcTemplate.update(sql, 
                labtech.getLbName(),
                labtech.getMobileNo(),
                labtech.getEmailId(),
                labtech.getGender(),
                labtech.getAge(),
                labtech.getExperience(),
                labtech.getPassword()
                );  // Added this parameter to the method call
                
            return labtech;
        }else {
        // You need to add an else block here or handle updates else {
            String sql = "UPDATE labtech SET Lb_name = ?, Mobile_no = ?, Email_id = ?, Gender = ?, Age = ?, Experience = ? WHERE LB_ID = ?";
            jdbcTemplate.update(sql,
            		labtech.getLbName(),
                    labtech.getMobileNo(),
                    labtech.getEmailId(),
                    labtech.getGender(),
                    labtech.getAge(),
                    labtech.getExperience(),                    
                    labtech.getLbId());
            return labtech;
        }
    
    }
    @Override
    public void deleteLabtech(int id) {
        String sql = "DELETE FROM labtech WHERE LB_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Labtech> getAllLabtechs() {
        String sql = "SELECT * FROM labtech";
        return jdbcTemplate.query(sql, labtechRowMapper);
    }


    @Override
    public Labtech updateLabtech(Labtech labtech) {
        if (labtech.getLbId() == 0) {
            throw new IllegalArgumentException("Labtech ID must be provided for update");
        }
        return saveLabtech(labtech);
    }

    
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM labtech WHERE LB_ID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
  
}