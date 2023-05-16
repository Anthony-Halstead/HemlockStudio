package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HemlockStudiosWebsite.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

    @Query(value="select * from customer where (username = ?1 or email = ?2) and password = ?3", nativeQuery = true) 
    public Customer findByUsernameOrEmailAndPassword(String username, String email, String password);

    @Query(value="select * from customer where (username = ?1 or email = ?2)", nativeQuery = true) 
    public Customer findByUsernameOrEmail(String username, String email);

}