/**
 * The Analytics class is an entity class that represents analytics data and has a one-to-one
 * relationship with the RevenueAnalytics class.
 */
package com.HemlockStudiosWebsite.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
// The `@Table(name = "analytics")` annotation is used to specify the name of the database table that
// will be mapped to the `Analytics` entity class. In this case, it specifies that the table name
// should be "analytics".
@Table(name = "analytics")
public class Analytics {

    @Id
    // The `@GeneratedValue(strategy = GenerationType.IDENTITY)` annotation is used to specify the
    // strategy for generating unique identifiers for the `id` field in the `Analytics` class.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // The `@OneToOne(cascade = CascadeType.ALL, mappedBy = "analytics")` annotation is used to define a
   // one-to-one relationship between the `Analytics` class and the `RevenueAnalytics` class.
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