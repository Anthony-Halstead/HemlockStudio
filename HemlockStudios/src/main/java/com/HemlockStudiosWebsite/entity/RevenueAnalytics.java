/**
 * The RevenueAnalytics class represents the revenue analytics data associated with an Analytics object
 * in a Hemlock Studios website.
 */
package com.HemlockStudiosWebsite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
// The `@Table(name = "revenue_analytics")` annotation is used to specify the name of the database
// table that will be associated with the `RevenueAnalytics` entity class. In this case, the table name
// is "revenue_analytics".
@Table(name = "revenue_analytics")
public class RevenueAnalytics {
   
  // The code snippet `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` are annotations
  // used in Java Persistence API (JPA) to specify that the `id` field in the `RevenueAnalytics` class
  // is the primary key of the entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  // The code snippet `@OneToOne` and `@JoinColumn(name = "analytics_id")` are annotations used in Java
  // Persistence API (JPA) to establish a one-to-one relationship between the `RevenueAnalytics` entity
  // and the `Analytics` entity.
    @OneToOne
    @JoinColumn(name = "analytics_id")
    private Analytics analytics;

   // The `@Column(name = "total_revenue")` annotation is used to specify the mapping of the
   // `totalRevenue` field to a column in the database table associated with the `RevenueAnalytics`
   // entity. In this case, the column name in the database table will be "total_revenue". This
   // annotation allows the JPA framework to automatically map the field to the corresponding column in
   // the database table when performing database operations.
    @Column(name = "total_revenue")
    private Double totalRevenue;

    public RevenueAnalytics() {

    }

    public Integer getId() {
        return id;
    }

    public Analytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
}
