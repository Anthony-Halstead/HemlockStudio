// This code is defining a repository interface for the `RevenueAnalytics` entity in a Spring Boot
// application.
package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HemlockStudiosWebsite.entity.RevenueAnalytics;

public interface RevenueAnalyticsRepo extends JpaRepository< RevenueAnalytics, Integer> {
    
}
