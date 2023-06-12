package com.HemlockStudiosWebsite.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_month")
    private String expirationMonth;

    @Column(name = "expiration_year")
    private String expirationYear;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "cvv")
    private String cvv;

@Column(name = "default_card")
private Boolean defaultCard;

    public Integer getId() {
        return id;
    }
   
    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
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


    public String getCvv() {
        return cvv;
    }


    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Boolean getDefaultCard() {
        return defaultCard;
    }

    public void setDefaultCard(Boolean defaultCard) {
        this.defaultCard = defaultCard;
    }

  

}
