// This code is defining a repository interface for the `Administrator` entity in a Spring Boot
// application.
package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HemlockStudiosWebsite.entity.Administrator;

public interface AdministratorRepo extends JpaRepository<Administrator, Integer>{

    /**
     * This function queries the database to find an administrator by their username or email,
     * password, and admin ID.
     * 
     * @param username The username of the administrator.
     * @param email The email parameter is used to search for an administrator by their email address.
     * @param password The password parameter is used to specify the password of the administrator that
     * you want to find.
     * @param predefinedAdminId The predefinedAdminId parameter is used to specify the admin_id value
     * that you want to search for in the database.
     * @return The method is returning an object of type Administrator.
     */
    @Query(value="select * from administrator where (username = ?1 or email = ?2) and password = ?3 and admin_id = ?4", nativeQuery = true) 
    public Administrator findByUsernameOrEmailAndPasswordAndAdminId(String username, String email, String password, String predefinedAdminId);
    
}