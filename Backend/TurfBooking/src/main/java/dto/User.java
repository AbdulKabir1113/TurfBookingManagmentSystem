package dto;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String profileImage;
    private String accountStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default Constructor
    public User() {
    }

    // Constructor for Registration
    public User(String fullName, String email, String phone, String password,
            String role, String profileImage, String accountStatus) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.profileImage = profileImage;
        this.accountStatus = accountStatus;
    }

    // Full Constructor
    public User(int userId, String fullName, String email, String phone,
            String password, String role, String profileImage,
            String accountStatus, Timestamp createdAt, Timestamp updatedAt) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.profileImage = profileImage;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
                + ", role=" + role + ", accountStatus=" + accountStatus + "]";
    }
}
