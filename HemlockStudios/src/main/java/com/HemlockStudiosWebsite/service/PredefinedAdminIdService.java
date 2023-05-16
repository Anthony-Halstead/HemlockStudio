package com.HemlockStudiosWebsite.service;

import com.HemlockStudiosWebsite.entity.PredefinedAdminId;
import com.HemlockStudiosWebsite.repo.PredefinedAdminIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredefinedAdminIdService {

    @Autowired
    PredefinedAdminIdRepo predefinedAdminIdRepo;

    public PredefinedAdminId findByAdminId(String adminId) {
        return predefinedAdminIdRepo.findByAdminId(adminId);
    }

    public PredefinedAdminId save(PredefinedAdminId predefinedAdminId) {
        return predefinedAdminIdRepo.save(predefinedAdminId);
    }

}