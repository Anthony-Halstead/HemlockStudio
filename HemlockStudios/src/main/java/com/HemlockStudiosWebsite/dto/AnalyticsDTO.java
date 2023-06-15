package com.HemlockStudiosWebsite.dto;

import java.util.List;

public class AnalyticsDTO {
        private List<ProductData> productDataList;
    private Double totalRevenue;

    // Getter and Setter methods
    public List<ProductData> getProductDataList() {
        return productDataList;
    }

    public void setProductDataList(List<ProductData> productDataList) {
        this.productDataList = productDataList;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
