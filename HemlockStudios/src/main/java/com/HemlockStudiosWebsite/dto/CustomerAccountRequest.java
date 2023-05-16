package com.HemlockStudiosWebsite.dto;



public class CustomerAccountRequest{
    private String username;
    private String email;
    private String password;
    private Boolean isSignedUp;

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

    public Boolean getIsSignedUp() {
        return isSignedUp;
    }
    public void setIsSignedUp(Boolean isSignedUp) {
        this.isSignedUp = isSignedUp;
    }
    
    @Override
    public String toString() {
        return "CustomerAccountRequest [username=" + username + ", email=" + email + ", password=" + password
                + "]";
    }

   

    
}