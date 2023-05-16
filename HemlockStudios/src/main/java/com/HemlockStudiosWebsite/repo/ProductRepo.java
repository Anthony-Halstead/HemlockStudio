package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HemlockStudiosWebsite.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    }

