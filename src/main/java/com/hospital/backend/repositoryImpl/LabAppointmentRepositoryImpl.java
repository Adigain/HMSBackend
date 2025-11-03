
package com.hospital.backend.repositoryImpl;

import com.hospital.backend.entity.LabAppointment;
import com.hospital.backend.repository.LabAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class LabAppointmentRepositoryImpl implements LabAppointmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<LabAppointment> rowMapper = (ResultSet rs, int rowNum) -> {
        LabAppointment a = new LabAppointment();
        a.setAppointmentId(rs.getInt("Appointment_ID"));
        a.setPId(rs.getInt("P_ID"));
        a.setTestId(rs.getInt("Test_ID"));
        a.setDrId(rs.getInt("DR_ID"));
        a.setLbId(rs.getObject("Lb_ID") != null ? rs.getInt("Lb_ID") : null);
        a.setAppointmentDate(rs.getDate("appointment_date"));
        a.setStatus(rs.getString("status"));
        a.setRemarks(rs.getString("remarks"));
        return a;
    };

    @Override
    public LabAppointment saveLabAppointment(LabAppointment a) {
        String sql = "INSERT INTO LabAppointment (P_ID, Test_ID, DR_ID, Lb_ID, appointment_date, status, remarks) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                a.getPId(),
                a.getTestId(),
                a.getDrId(),
                a.getLbId(),
                a.getAppointmentDate(),
                a.getStatus(),
                a.getRemarks());
        return a;
    }

    @Override
    public Optional<LabAppointment> getLabAppointmentById(int id) {
        String sql = "SELECT * FROM LabAppointment WHERE Appointment_ID = ?";
        List<LabAppointment> list = jdbcTemplate.query(sql, rowMapper, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public List<LabAppointment> getAllLabAppointments() {
        return jdbcTemplate.query("SELECT * FROM LabAppointment", rowMapper);
    }

    @Override
    public void deleteLabAppointment(int id) {
        jdbcTemplate.update("DELETE FROM LabAppointment WHERE Appointment_ID = ?", id);
    }

    @Override
    public boolean existsById(int id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM LabAppointment WHERE Appointment_ID = ?",
                Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public LabAppointment updateLabAppointment(LabAppointment a) {
        String sql = "UPDATE LabAppointment SET P_ID=?, Test_ID=?, DR_ID=?, Lb_ID=?, appointment_date=?, status=?, remarks=? WHERE Appointment_ID=?";
        jdbcTemplate.update(sql,
                a.getPId(),
                a.getTestId(),
                a.getDrId(),
                a.getLbId(),
                a.getAppointmentDate(),
                a.getStatus(),
                a.getRemarks(),
                a.getAppointmentId());
        return a;
    }

    @Override
    public List<LabAppointment> searchLabAppointmentsByPatientName(String name) {
        String sql = "SELECT la.* FROM LabAppointment la JOIN patient p ON la.P_ID=p.P_ID WHERE LOWER(p.Name) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, rowMapper, "%" + name + "%");
    }

    // ✅ NEW METHODS

    @Override
    public List<LabAppointment> getLabAppointmentsByDoctorId(int doctorId) {
        String sql = "SELECT * FROM LabAppointment WHERE DR_ID = ?";
        return jdbcTemplate.query(sql, rowMapper, doctorId);
    }

    @Override
    public List<LabAppointment> getLabAppointmentsByLabTechId(int labTechId) {
        String sql = "SELECT * FROM LabAppointment WHERE Lb_ID = ?";
        return jdbcTemplate.query(sql, rowMapper, labTechId);
    }

    @Override
    public List<LabAppointment> getLabAppointmentsByPatientId(int patientId) {
        String sql = "SELECT * FROM LabAppointment WHERE P_ID = ?";
        return jdbcTemplate.query(sql, rowMapper, patientId);
    }

    @Override
    public List<LabAppointment> getAllCompletedAppointments() {
        String sql = "SELECT * FROM LabAppointment WHERE LOWER(status) = 'completed'";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<LabAppointment> getAllPastAppointments() {
        String sql = "SELECT * FROM LabAppointment WHERE appointment_date < CURRENT_DATE";
        return jdbcTemplate.query(sql, rowMapper);
    }
    @Override
    public List<LabAppointment> getAllUpcomingOrPendingAppointments() {
        String sql = "SELECT * FROM LabAppointment WHERE appointment_date >= CURRENT_DATE OR LOWER(status) = 'pending'";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
