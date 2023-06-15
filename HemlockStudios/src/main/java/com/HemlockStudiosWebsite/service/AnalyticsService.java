package com.HemlockStudiosWebsite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.dto.AnalyticsDTO;
import com.HemlockStudiosWebsite.dto.ProductData;
import com.HemlockStudiosWebsite.dto.ProductSaleInfoDTO;
import com.HemlockStudiosWebsite.entity.Analytics;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.entity.ProductAnalytics;
import com.HemlockStudiosWebsite.entity.RevenueAnalytics;
import com.HemlockStudiosWebsite.events.PurchaseMadeEvent;
import com.HemlockStudiosWebsite.events.TotalEvent;
import com.HemlockStudiosWebsite.repo.AnalyticsRepo;
import com.HemlockStudiosWebsite.repo.ProductAnalyticsRepo;
import com.HemlockStudiosWebsite.repo.RevenueAnalyticsRepo;

@Service
public class AnalyticsService {

    @Autowired
    private ProductAnalyticsRepo productAnalyticsRepo;
    @Autowired
    private AnalyticsRepo analyticsRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private RevenueAnalyticsRepo revenueAnalyticsRepo;


    @Async
    @EventListener
    public void handlePurchaseMadeEvent(PurchaseMadeEvent event) {
        for (ProductSaleInfoDTO sale : event.getProductSales()) {
            incrementSoldQuantity(sale.getProductId(), sale.getQuantity());
        }
    }

    @Async
    @EventListener
    public void handleTotalEvent(TotalEvent event) {
            incrementTotalRevenue(event.getTotal());
    }

    public void incrementSoldQuantity(Integer productId, Integer quantity) {
  
        Product product = productService.getProductById(productId);
    
        ProductAnalytics productAnalytics = productAnalyticsRepo.findByProductId(productId);
        if (productAnalytics == null) {
            productAnalytics = new ProductAnalytics();
            productAnalytics.setProduct(product);
            productAnalytics.setQuantitySold(quantity);

             Analytics analytics = analyticsRepo.findById(1).orElse(new Analytics());
    analyticsRepo.save(analytics); // save analytics before setting it to productAnalytics
    productAnalytics.setAnalytics(analytics);
        } else {
            productAnalytics.setQuantitySold(productAnalytics.getQuantitySold() + quantity);
        }

        productAnalyticsRepo.save(productAnalytics);
    }

    public void incrementTotalRevenue(Double total) {
    System.out.println("In the increment total service path" + total);
    RevenueAnalytics revenueAnalytics = revenueAnalyticsRepo.findById(1).orElse(new RevenueAnalytics());
    
    if (revenueAnalytics.getTotalRevenue() == null) {
        revenueAnalytics.setTotalRevenue(total);

        Analytics analytics = analyticsRepo.findById(1).orElse(new Analytics());
        analyticsRepo.save(analytics); // save analytics before setting it to revenueAnalytics
        revenueAnalytics.setAnalytics(analytics);
    } else {
        revenueAnalytics.setTotalRevenue(revenueAnalytics.getTotalRevenue() + total);
        
        // Save the analytics if they exist but haven't been persisted yet
        if (revenueAnalytics.getAnalytics() != null && revenueAnalytics.getAnalytics().getId() == null) {
            analyticsRepo.save(revenueAnalytics.getAnalytics());
        }
    }
    revenueAnalyticsRepo.save(revenueAnalytics);
}

 public AnalyticsDTO getAnalytics() {
    // Fetch the Analytics entity
    Analytics analytics = analyticsRepo.findById(1).orElse(null);
    
    if (analytics == null) {
        return null; // or you can throw an exception
    }

    // Create DTO and populate with data from entity
    AnalyticsDTO analyticsDTO = new AnalyticsDTO();

    // Fetch and populate product data
    List<ProductData> productDataList = new ArrayList<>();
    for (ProductAnalytics productAnalytics : analytics.getProductAnalyticsList()) {
        ProductData productData = new ProductData();
        productData.setProductId(productAnalytics.getProduct().getId());
        productData.setProductName(productAnalytics.getProduct().getName()); // assuming the Product entity has a getName() method
        productData.setQuantitySold(productAnalytics.getQuantitySold());
        productDataList.add(productData);
    }
    analyticsDTO.setProductDataList(productDataList);

    // Fetch and populate revenue analytics data
    if (analytics.getRevenueAnalytics() != null) {
        analyticsDTO.setTotalRevenue(analytics.getRevenueAnalytics().getTotalRevenue());
    }

    return analyticsDTO;
}
}
