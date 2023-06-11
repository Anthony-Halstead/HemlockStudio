package com.HemlockStudiosWebsite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
    ProductAnalyticsRepo productAnalyticsRepo;
    @Autowired
    AnalyticsRepo analyticsRepo;
    @Autowired
    ProductService productService;
    @Autowired
    RevenueAnalyticsRepo revenueAnalyticsRepo;


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
        // Fetch the Product object from the database
        Product product = productService.getProductById(productId);
    
        ProductAnalytics productAnalytics = productAnalyticsRepo.findByProductId(productId);
        if (productAnalytics == null) {
            productAnalytics = new ProductAnalytics();
            productAnalytics.setProduct(product);
            productAnalytics.setQuantitySold(quantity);
        } else {
            productAnalytics.setQuantitySold(productAnalytics.getQuantitySold() + quantity);
        }
    
        productAnalyticsRepo.save(productAnalytics);
    }

    public void incrementTotalRevenue(Double total)
    {
        Optional<RevenueAnalytics> optionalRevenueAnalytics = revenueAnalyticsRepo.findById(1);
        if(optionalRevenueAnalytics.isPresent()){
            RevenueAnalytics revenueAnalytics = optionalRevenueAnalytics.get();
            revenueAnalytics.setTotalRevenue(revenueAnalytics.getTotalRevenue()+total);
        } else {
            RevenueAnalytics revenueAnalytics = new RevenueAnalytics();
            revenueAnalytics.setTotalRevenue(total);
            revenueAnalyticsRepo.save(revenueAnalytics);
        }
    }

}
