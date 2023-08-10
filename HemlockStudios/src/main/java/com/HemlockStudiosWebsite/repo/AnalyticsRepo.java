// This code is defining a repository interface called `AnalyticsRepo` in the package
// `com.HemlockStudiosWebsite.repo`.
package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HemlockStudiosWebsite.entity.Analytics;

public interface AnalyticsRepo extends JpaRepository<Analytics, Integer>{
    
}
