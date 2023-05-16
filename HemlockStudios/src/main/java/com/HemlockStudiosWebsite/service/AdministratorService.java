
package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HemlockStudiosWebsite.entity.Administrator;
import com.HemlockStudiosWebsite.entity.PredefinedAdminId;
import com.HemlockStudiosWebsite.repo.AdministratorRepo;

@Service
public class AdministratorService {
    
    @Autowired
    AdministratorRepo administratorRepo;

    @Autowired
    PredefinedAdminIdService predefinedAdminIdService;

    public Administrator signUpAsAdministrator(String username, String email, String password, String predefinedAdminId) {
        // Check if the predefinedAdminId exists and is not used
        PredefinedAdminId predefinedId = predefinedAdminIdService.findByAdminId(predefinedAdminId);
    
        if (predefinedId == null || predefinedId.getIsUsed()) {
            throw new RuntimeException("Invalid or unavailable administrator ID.");
        }
    
        // Create a new Administrator instance
        Administrator admin = new Administrator();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(password); 
        admin.setAdminId(predefinedAdminId);
    
        // Save the administrator to the database
        Administrator savedAdmin = administratorRepo.save(admin);
    
        // Mark the predefinedAdminId as used
        predefinedId.setIsUsed(true);
        predefinedAdminIdService.save(predefinedId);
    
        return savedAdmin;
    }

    public List<Administrator> getAll()
    {
        return administratorRepo.findAll();
    }

    public Administrator save(Administrator administrator){
        return administratorRepo.save(administrator);
    }

    public Administrator update(Administrator administrator) throws Exception
    {
        if(administrator.getId() != null)
        {
        return administratorRepo.save(administrator);
        }

        throw new Exception("Account does not exist, id not present");
    }

    public Administrator findById(Integer administratorId) throws Error
    {
        if(administratorRepo.findById(administratorId).isPresent()){
            return administratorRepo.findById(administratorId).get();
        }
     
        throw new Error("Administrator was not found");
    }

    public void deleteAdministratorById(Integer id) {
        Administrator administrator = administratorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrator not found"));
        PredefinedAdminId predefinedAdminId = predefinedAdminIdService.findByAdminId(administrator.getAdminId());
        predefinedAdminId.setIsUsed(false);
        predefinedAdminIdService.save(predefinedAdminId);
        administratorRepo.deleteById(id);
    }

    public Administrator signInAsAdministrator(String username, String email, String password, String predefinedAdminId) {
        Administrator administrator = administratorRepo.findByUsernameOrEmailAndPasswordAndAdminId(username, email, password, predefinedAdminId);
        if (administrator == null) 
          {throw new IllegalArgumentException("Invalid username or email.");}
        else if (!administrator.getPassword().equals(password)) 
          {throw new IllegalArgumentException("Invalid password.");} 
        else if (!administrator.getAdminId().equals(predefinedAdminId)) 
          {throw new IllegalArgumentException("Invalid Admin ID.");}
        else
          {return administrator;}
    }

    public Administrator updateAdministrator(Integer id, String username, String email) {
        Administrator administrator = administratorRepo.findById(id).orElse(null);
        
        administrator.setUsername(username);
        administrator.setEmail(email);
        return administratorRepo.save(administrator);
    }

}
