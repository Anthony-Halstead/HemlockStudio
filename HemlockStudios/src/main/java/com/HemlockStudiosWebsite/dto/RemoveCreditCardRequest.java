package com.HemlockStudiosWebsite.dto;

public class RemoveCreditCardRequest {
    private Integer customerId;
    private Integer creditCardId;
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getCreditCardId() {
        return creditCardId;
    }
    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    
}
