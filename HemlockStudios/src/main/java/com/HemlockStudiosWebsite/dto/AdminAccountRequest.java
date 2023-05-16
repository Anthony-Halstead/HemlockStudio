package com.HemlockStudiosWebsite.dto;

public class AdminAccountRequest {
    private String username;
    private String email;
    private String password;
    private String predefinedAdminId;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPredefinedAdminId() {
        return predefinedAdminId;
    }
    public void setPredefinedAdminId(String predefinedAdminId) {
        this.predefinedAdminId = predefinedAdminId;
    }
    
    @Override
    public String toString() {
        return "AdminAccountRequest [username=" + username + ", email=" + email + ", password=" + password
                + ", predefinedAdminId=" + predefinedAdminId + "]";
    }   

    
}