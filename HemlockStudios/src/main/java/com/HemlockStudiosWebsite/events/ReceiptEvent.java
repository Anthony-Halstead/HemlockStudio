package com.HemlockStudiosWebsite.events;

import java.util.List;

import com.HemlockStudiosWebsite.dto.ProductSaleInfoDTO;

public class ReceiptEvent {
    private final String userEmail;
    private final List<ProductSaleInfoDTO> productSales;

    public ReceiptEvent(String userEmail, List<ProductSaleInfoDTO> productSales) {
        this.userEmail = userEmail;
        this.productSales = productSales;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public List<ProductSaleInfoDTO> getProductSales() {
        return productSales;
    }
}