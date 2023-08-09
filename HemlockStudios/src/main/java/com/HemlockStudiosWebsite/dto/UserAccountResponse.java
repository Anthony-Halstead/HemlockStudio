package com.HemlockStudiosWebsite.dto;

public class UserAccountResponse  {
    private Integer id;
    private String username;
    private String email;
    private Boolean isSignedUp;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public Boolean getIsSignedUp() {
        return isSignedUp;
    }
    public void setIsSignedUp(Boolean isSignedUp) {
        this.isSignedUp = isSignedUp;
    }
   
 
}