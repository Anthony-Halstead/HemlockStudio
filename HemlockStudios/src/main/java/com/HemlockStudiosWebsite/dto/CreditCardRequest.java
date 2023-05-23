package com.HemlockStudiosWebsite.dto;

public class CreditCardRequest {
    private Integer userId;
    private String cardNumber;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cardHolderName;
    private String cvv;


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public Integer getExpirationMonth() {
        return expirationMonth;
    }
    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }
    public Integer getExpirationYear() {
        return expirationYear;
    }
    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}
