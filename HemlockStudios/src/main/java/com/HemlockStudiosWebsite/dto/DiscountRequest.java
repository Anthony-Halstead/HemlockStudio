package com.HemlockStudiosWebsite.dto;

public class DiscountRequest {
    private Integer cartId;
    private String couponCode;
    private Integer userId;

    public Integer getCartId() {
        return cartId;
    }
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public String getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    public Integer getUserId() {
        return userId;
    }
  

   
}