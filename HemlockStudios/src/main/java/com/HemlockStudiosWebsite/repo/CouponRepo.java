package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HemlockStudiosWebsite.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    
    @Query(value="select * from coupon where discount_code =?1", nativeQuery = true) 
    public Coupon  findByCouponCode(String couponCode);
}