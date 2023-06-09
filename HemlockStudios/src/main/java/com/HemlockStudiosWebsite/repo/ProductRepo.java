package com.HemlockStudiosWebsite.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HemlockStudiosWebsite.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value="select * from product where id = ?1", nativeQuery = true)
    public Product findReference(Integer id);
    }

