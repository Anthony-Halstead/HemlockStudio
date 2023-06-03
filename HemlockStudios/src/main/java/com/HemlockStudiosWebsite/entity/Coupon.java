package com.HemlockStudiosWebsite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discount_code", unique = true, nullable = false)
    private String couponCode;

    @Column(name = "discount_value", nullable = false)
    private Double discountValue;


public Double getDiscountValue() {
    return discountValue;
}

public void setDiscountValue(Double discountValue) {
    this.discountValue = discountValue;
}

  
    public String getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
