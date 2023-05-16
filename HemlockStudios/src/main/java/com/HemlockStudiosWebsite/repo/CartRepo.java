package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HemlockStudiosWebsite.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}