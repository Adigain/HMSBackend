package com.hospital.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class LabAppointment {

    private int appointmentId;

    @JsonProperty("pId")
    private Integer pId;

    @JsonProperty("testId")
    private Integer testId;

    @JsonProperty("drId")
    private Integer drId;

    @JsonProperty("lbId")
    private Integer lbId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    private String status;
    private String remarks;

    public LabAppointment() {
        this.status = "Pending";
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Integer getLbId() {
        return lbId;
    }

    public void setLbId(Integer lbId) {
        this.lbId = lbId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status != null) ? status : "Pending";
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "LabAppointment{" +
                "appointmentId=" + appointmentId +
                ", pId=" + pId +
                ", testId=" + testId +
                ", drId=" + drId +
                ", lbId=" + lbId +
                ", appointmentDate=" + appointmentDate +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
