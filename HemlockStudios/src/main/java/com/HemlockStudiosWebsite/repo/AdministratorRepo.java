package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HemlockStudiosWebsite.entity.Administrator;

public interface AdministratorRepo extends JpaRepository<Administrator, Integer>{
//the information in the query has to match the column in your database table in this case the 'admin' table
    @Query(value="select * from administrator where (username = ?1 or email = ?2) and password = ?3 and admin_id = ?4", nativeQuery = true) 
    public Administrator findByUsernameOrEmailAndPasswordAndAdminId(String username, String email, String password, String predefinedAdminId);
    
}