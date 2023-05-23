package com.HemlockStudiosWebsite.dto;

public class RemoveCreditCardRequest {
    private Integer userId;
    private Integer creditCardId;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCreditCardId() {
        return creditCardId;
    }
    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    
}
