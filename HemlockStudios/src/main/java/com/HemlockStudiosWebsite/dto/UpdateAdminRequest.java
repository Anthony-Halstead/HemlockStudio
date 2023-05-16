package com.HemlockStudiosWebsite.dto;

public class UpdateAdminRequest {
    private String username;
    private String email;
    private Integer id;
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPredefinedAdminId() {
        return predefinedAdminId;
    }
    public void setPredefinedAdminId(String predefinedAdminId) {
        this.predefinedAdminId = predefinedAdminId;
    }
}
