package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HemlockStudiosWebsite.entity.ProductAnalytics;

public interface ProductAnalyticsRepo extends JpaRepository<ProductAnalytics, Integer>{
    
    @Query(value = "SELECT * FROM product_analytics WHERE product_id = ?1", nativeQuery = true)
    ProductAnalytics findByProductId(Integer productId);

}
