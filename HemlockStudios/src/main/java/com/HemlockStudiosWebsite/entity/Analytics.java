package com.HemlockStudiosWebsite.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    // other fields, constructors, getters, setters
}