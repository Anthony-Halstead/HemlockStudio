package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.HemlockStudiosWebsite.entity.CartItem;


public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    


     @Query(value="select * from cart_item where cart_id = ?1 and product_id = ?2", nativeQuery = true)
    CartItem findByCartIdAndProductId(Integer cartId, Integer productId);


}
