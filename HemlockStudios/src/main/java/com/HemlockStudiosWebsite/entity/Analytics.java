package com.HemlockStudiosWebsite.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "analytics")
    private RevenueAnalytics revenueAnalytics;

    public Integer getId() {
        return id;
    }

    public RevenueAnalytics getRevenueAnalytics() {
        return revenueAnalytics;
    }

    public void setRevenueAnalytics(RevenueAnalytics revenueAnalytics) {
        this.revenueAnalytics = revenueAnalytics;
    }
}