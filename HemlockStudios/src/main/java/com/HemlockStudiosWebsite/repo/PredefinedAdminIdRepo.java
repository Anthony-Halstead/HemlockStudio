package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HemlockStudiosWebsite.entity.PredefinedAdminId;

public interface PredefinedAdminIdRepo extends JpaRepository<PredefinedAdminId, Integer> {

    // @Query(value = "SELECT * FROM administrator WHERE admin_id = ?1", nativeQuery = true)
    // public Administrator findByAdminId(String adminId);
    PredefinedAdminId findByAdminId(String adminId);
}