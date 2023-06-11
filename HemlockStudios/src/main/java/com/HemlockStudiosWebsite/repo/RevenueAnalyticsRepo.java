package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HemlockStudiosWebsite.entity.RevenueAnalytics;

public interface RevenueAnalyticsRepo extends JpaRepository< RevenueAnalytics, Integer> {
    
}
