package com.HemlockStudiosWebsite.events;

import java.util.List;

import com.HemlockStudiosWebsite.dto.ProductSaleInfoDTO;

public class PurchaseMadeEvent {
    private final List<ProductSaleInfoDTO> productSales;

    public PurchaseMadeEvent(List<ProductSaleInfoDTO> productSales) {
        this.productSales = productSales;
    }

    public List<ProductSaleInfoDTO> getProductSales() {
        return productSales;
    }
}