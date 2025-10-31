// package com.hospital.backend.entity;

// public class Doctor {
// 	private int drId;
// 	private String drName;
//     private String mobileNo;
//     private String emailId;
//     private String gender;
//     private Integer age;
//     private Integer experience;
//     private String password;
//     private Integer spId;
//     private String picture;
//     public int getDrId() {
// 		return drId;
// 	}

// 	public void setDrId(int drId) {
// 		this.drId = drId;
// 	}

// 	public String getDrName() {
// 		return drName;
// 	}

// 	public void setDrName(String drName) {
// 		this.drName = drName;
// 	}

// 	public String getMobileNo() {
// 		return mobileNo;
// 	}

// 	public void setMobileNo(String mobileNo) {
// 		this.mobileNo = mobileNo;
// 	}

// 	public String getEmailId() {
// 		return emailId;
// 	}

// 	public void setEmailId(String emailId) {
// 		this.emailId = emailId;
// 	}

// 	public String getGender() {
// 		return gender;
// 	}

// 	public void setGender(String gender) {
// 		this.gender = gender;
// 	}

// 	public Integer getAge() {
// 		return age;
// 	}

// 	public void setAge(Integer age) {
// 		this.age = age;
// 	}

// 	public Integer getExperience() {
// 		return experience;
// 	}

// 	public void setExperience(Integer experience) {
// 		this.experience = experience;
// 	}

// 	public String getPassword() {
// 		return password;
// 	}

// 	public void setPassword(String password) {
// 		this.password = password;
// 	}

// 	public Integer getSpId() {
// 		return spId;
// 	}

// 	public void setSpId(Integer spId) {
// 		this.spId = spId;
// 	}

// 	public String getPicture() {
// 		return picture;
// 	}

// 	public void setPicture(String picture) {
// 		this.picture = picture;
// 	}

    
//     // Default constructor
//     public Doctor() {}

    


// }

package com.hospital.backend.entity;

public class Doctor {
    private int drId;
    private String drName;
    private String mobileNo;
    private String emailId;
    private String gender;
    private Integer age;
    private Integer experience;
    private String password;
    private Integer spId;
    private String picture;

    
    public Doctor() {}

  
    public Doctor(int drId, String drName, String mobileNo, String emailId, String gender,
                  Integer age, Integer experience, String password, Integer spId, String picture) {
        this.drId = drId;
        this.drName = drName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.gender = gender;
        this.age = age;
        this.experience = experience;
        this.password = password;
        this.spId = spId;
        this.picture = picture;
    }

    
    public int getDrId() { return drId; }
    public void setDrId(int drId) { this.drId = drId; }

    public String getDrName() { return drName; }
    public void setDrName(String drName) { this.drName = drName; }

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

    public Integer getSpId() { return spId; }
    public void setSpId(Integer spId) { this.spId = spId; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    @Override
    public String toString() {
        return "Doctor{" +
                "drId=" + drId +
                ", drName='" + drName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

