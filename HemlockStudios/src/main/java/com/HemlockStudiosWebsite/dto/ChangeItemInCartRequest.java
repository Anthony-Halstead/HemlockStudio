package com.HemlockStudiosWebsite.dto;

public class ChangeItemInCartRequest {
    private Integer cartId;
    private Integer productId;
    
    public Integer getCartId() {
        return cartId;
    }
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
