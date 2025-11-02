package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.Pharmacist;
import com.hospital.backend.entity.Patient;
import com.hospital.backend.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PharmacistRepositoryImpl implements PharmacistRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Pharmacist> pharmacistRowMapper = new RowMapper<Pharmacist>() {
        @Override
        public Pharmacist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setPhId(rs.getInt("PH_ID"));
            pharmacist.setPhName(rs.getString("Ph_name"));
            pharmacist.setMobileNo(rs.getString("Mobile_no"));
            pharmacist.setEmailId(rs.getString("Email_id"));
            pharmacist.setGender(rs.getString("Gender"));
            pharmacist.setAge(rs.getInt("Age"));
            pharmacist.setExperience(rs.getInt("Experience"));
            pharmacist.setPassword(rs.getString("Password"));            
            return pharmacist;
        }
    };

    @Override
    public Optional<Pharmacist> getPharmacistById(int id) {
        String sql = "SELECT * FROM pharmacist WHERE PH_ID = ?";
        List<Pharmacist> pharmacists = jdbcTemplate.query(sql, pharmacistRowMapper, id);
        return pharmacists.isEmpty() ? Optional.empty() : Optional.of(pharmacists.get(0));
    }

    @Override
    public Optional<Pharmacist> findByEmail(String email) {
        String sql = "SELECT * FROM pharmacist WHERE Email_id = ?";
        List<Pharmacist> pharmacists = jdbcTemplate.query(sql, pharmacistRowMapper, email);
        return pharmacists.isEmpty() ? Optional.empty() : Optional.of(pharmacists.get(0));
    }



    @Override
    public List<Pharmacist> searchPharmacistsByName(String name) {
        String sql = "SELECT * FROM pharmacist WHERE LOWER(Ph_name) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, pharmacistRowMapper, "%" + name + "%");
    }

    @Override
    public Pharmacist savePharmacist(Pharmacist pharmacist) {
        if (pharmacist.getPhId() == 0) {
            String sql = "INSERT INTO pharmacist (Ph_name, Mobile_no, Email_id, Gender, Age, Experience, Password) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            jdbcTemplate.update(sql, 
                pharmacist.getPhName(),
                pharmacist.getMobileNo(),
                pharmacist.getEmailId(),
                pharmacist.getGender(),
                pharmacist.getAge(),
                pharmacist.getExperience(),
                pharmacist.getPassword()
                );  // Added this parameter to the method call
                
            return pharmacist;
        }else {
        // You need to add an else block here or handle updates else {
            String sql = "UPDATE pharmacist SET Ph_name = ?, Mobile_no = ?, Email_id = ?, Gender = ?, Age = ?, Experience = ? WHERE PH_ID = ?";
            jdbcTemplate.update(sql,
            		pharmacist.getPhName(),
                    pharmacist.getMobileNo(),
                    pharmacist.getEmailId(),
                    pharmacist.getGender(),
                    pharmacist.getAge(),
                    pharmacist.getExperience(),                    
                    pharmacist.getPhId());
            return pharmacist;
        }
    
    }
    @Override
    public void deletePharmacist(int id) {
        String sql = "DELETE FROM pharmacist WHERE PH_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Pharmacist> getAllPharmacists() {
        String sql = "SELECT * FROM pharmacist";
        return jdbcTemplate.query(sql, pharmacistRowMapper);
    }


    @Override
    public Pharmacist updatePharmacist(Pharmacist pharmacist) {
        if (pharmacist.getPhId() == 0) {
            throw new IllegalArgumentException("Pharmacist ID must be provided for update");
        }
        return savePharmacist(pharmacist);
    }

    
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM pharmacist WHERE PH_ID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
  
}