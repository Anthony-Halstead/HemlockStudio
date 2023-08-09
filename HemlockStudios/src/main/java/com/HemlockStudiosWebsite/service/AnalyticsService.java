package com.HemlockStudiosWebsite.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HemlockStudiosWebsite.dto.AnalyticsDTO;
import com.HemlockStudiosWebsite.entity.Analytics;
import com.HemlockStudiosWebsite.entity.RevenueAnalytics;
import com.HemlockStudiosWebsite.repo.AnalyticsRepo;
import com.HemlockStudiosWebsite.repo.RevenueAnalyticsRepo;
@Service
public class AnalyticsService {
    @Autowired
    private AnalyticsRepo analyticsRepo;
    @Autowired
    private RevenueAnalyticsRepo revenueAnalyticsRepo;

    public void incrementTotalRevenue(Double total) {
    System.out.println("In the increment total service path" + total);
    RevenueAnalytics revenueAnalytics = revenueAnalyticsRepo.findById(1).orElse(new RevenueAnalytics());
    
    if (revenueAnalytics.getTotalRevenue() == null) {
        revenueAnalytics.setTotalRevenue(total);

        Analytics analytics = analyticsRepo.findById(1).orElse(new Analytics());
        analyticsRepo.save(analytics);
        revenueAnalytics.setAnalytics(analytics);
    } else {
        revenueAnalytics.setTotalRevenue(revenueAnalytics.getTotalRevenue() + total);     
        if (revenueAnalytics.getAnalytics() != null && revenueAnalytics.getAnalytics().getId() == null) {
            analyticsRepo.save(revenueAnalytics.getAnalytics());
        }
    }
    revenueAnalyticsRepo.save(revenueAnalytics);
}
 public AnalyticsDTO getAnalytics() {
    Analytics analytics = analyticsRepo.findById(1).orElse(null);
    if (analytics == null) {
        return null; 
    }
    AnalyticsDTO analyticsDTO = new AnalyticsDTO();
    if (analytics.getRevenueAnalytics() != null) {
        analyticsDTO.setTotalRevenue(analytics.getRevenueAnalytics().getTotalRevenue());
    }
    return analyticsDTO;
}
}
