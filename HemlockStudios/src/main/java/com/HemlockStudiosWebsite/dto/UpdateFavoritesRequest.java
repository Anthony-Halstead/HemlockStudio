package com.HemlockStudiosWebsite.dto;

public class UpdateFavoritesRequest {
    private Integer customerId;
    private Integer productId;
    
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCartId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
