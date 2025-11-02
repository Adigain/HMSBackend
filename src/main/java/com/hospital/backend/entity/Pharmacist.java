package com.hospital.backend.entity;

public class Pharmacist {

    private int phId;
    private String phName;
    private String mobileNo;
    private String emailId;
    private String gender;
    private Integer age;
    private Integer experience;
    private String password;

    public Pharmacist() {}

  
    public Pharmacist(int phId, String phName, String mobileNo, String emailId, String gender,
                  Integer age, Integer experience, String password) {
        this.phId = phId;
        this.phName = phName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.gender = gender;
        this.age = age;
        this.experience = experience;
        this.password = password;
    }
    
    
    public int getPhId() { return phId; }
    public void setPhId(int phId) { this.phId = phId; }

    public String getPhName() { return phName; }
    public void setPhName(String phName) { this.phName = phName; }

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
        return "Pharmacist{" +
                "phId=" + phId +
                ", phName='" + phName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    
}
