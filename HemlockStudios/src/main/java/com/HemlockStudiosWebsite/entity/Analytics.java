package com.HemlockStudiosWebsite.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analytics")
    private List<ProductAnalytics> productAnalyticsList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analytics")
    private List<CouponAnalytics> couponAnalyticsList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "analytics")
    private RevenueAnalytics revenueAnalytics;

    public Integer getId() {
        return id;
    }

    public List<ProductAnalytics> getProductAnalyticsList() {
        return productAnalyticsList;
    }

    public void setProductAnalyticsList(List<ProductAnalytics> productAnalyticsList) {
        this.productAnalyticsList = productAnalyticsList;
    }

    public List<CouponAnalytics> getCouponAnalyticsList() {
        return couponAnalyticsList;
    }

    public void setCouponAnalyticsList(List<CouponAnalytics> couponAnalyticsList) {
        this.couponAnalyticsList = couponAnalyticsList;
    }

    public RevenueAnalytics getRevenueAnalytics() {
        return revenueAnalytics;
    }

    public void setRevenueAnalytics(RevenueAnalytics revenueAnalytics) {
        this.revenueAnalytics = revenueAnalytics;
    }
   

    
}