/**
 * The `AnalyticsService` class is a Spring service that handles analytics-related operations, such as
 * incrementing total revenue and retrieving analytics data.
 */
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

  /**
   * The function increments the total revenue by adding the provided amount to the existing total
   * revenue, and updates the associated analytics data.
   * 
   * @param total The "total" parameter is a Double value representing the amount to be added to the
   * total revenue.
   */
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
/**
 * The function retrieves analytics data from the database and maps it to an AnalyticsDTO object,
 * returning the total revenue if available.
 * 
 * @return The method is returning an instance of the AnalyticsDTO class.
 */
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
