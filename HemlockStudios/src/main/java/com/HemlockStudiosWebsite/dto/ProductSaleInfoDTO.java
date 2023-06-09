package com.HemlockStudiosWebsite.dto;

public class ProductSaleInfoDTO {
    private Integer productId;
    private Integer quantity;

    public ProductSaleInfoDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}