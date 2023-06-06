package com.HemlockStudiosWebsite.dto;

public class UpdateCreditCardRequest {
    private Integer creditCardId;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cardHolderName;
    private String cvv;

  

    public Integer getCreditCardId() {
        return creditCardId;
    }



    public String getCardNumber() {
        return cardNumber;
    }



    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }



    public String getExpirationMonth() {
        return expirationMonth;
    }



    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }



    public String getExpirationYear() {
        return expirationYear;
    }



    public void setExpirationYear(String expirationYear) {
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
