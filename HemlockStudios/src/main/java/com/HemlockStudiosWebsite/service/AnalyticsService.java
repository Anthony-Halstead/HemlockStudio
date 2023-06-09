package com.HemlockStudiosWebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.dto.ProductSaleInfoDTO;
import com.HemlockStudiosWebsite.entity.Analytics;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.entity.ProductAnalytics;
import com.HemlockStudiosWebsite.events.PurchaseMadeEvent;
import com.HemlockStudiosWebsite.repo.AnalyticsRepo;
import com.HemlockStudiosWebsite.repo.ProductAnalyticsRepo;

@Service
public class AnalyticsService {

    @Autowired
    ProductAnalyticsRepo productAnalyticsRepo;
    @Autowired
    AnalyticsRepo analyticsRepo;
    @Autowired
    ProductService productService;

    @Async
    @EventListener
    public void handlePurchaseMadeEvent(PurchaseMadeEvent event) {
        for (ProductSaleInfoDTO sale : event.getProductSales()) {
            incrementSoldQuantity(sale.getProductId(), sale.getQuantity());
        }
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
}
