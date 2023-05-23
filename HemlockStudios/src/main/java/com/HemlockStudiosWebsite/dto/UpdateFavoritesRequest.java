package com.HemlockStudiosWebsite.dto;

public class UpdateFavoritesRequest {
    private Integer userId;
    private Integer productId;
    
    public Integer getUserId() {
        return userId;
    }
    public void setCartId(Integer userId) {
        this.userId = userId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
