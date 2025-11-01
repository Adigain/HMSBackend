package com.hospital.backend.entity;

public class Labtech {

    private int lbId;
    private String lbName;
    private String mobileNo;
    private String emailId;
    private String gender;
    private Integer age;
    private Integer experience;
    private String password;

    public Labtech() {}

  
    public Labtech(int lbId, String lbName, String mobileNo, String emailId, String gender,
                  Integer age, Integer experience, String password) {
        this.lbId = lbId;
        this.lbName = lbName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.gender = gender;
        this.age = age;
        this.experience = experience;
        this.password = password;
    }
    
    
    public int getLbId() { return lbId; }
    public void setLbId(int lbId) { this.lbId = lbId; }

    public String getLbName() { return lbName; }
    public void setLbName(String lbName) { this.lbName = lbName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Labtech{" +
                "lbId=" + lbId +
                ", lbName='" + lbName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    
}
