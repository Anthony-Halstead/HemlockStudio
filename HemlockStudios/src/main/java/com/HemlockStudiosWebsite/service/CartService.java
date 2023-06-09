package com.HemlockStudiosWebsite.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.repo.CartRepo;


@Service
public class CartService {

@Autowired
CartRepo cartRepo;
@Autowired
UserService userService;

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepo.save(cart);
    }

    public void deleteCartById(Integer id) {
        cartRepo.deleteById(id);
    }

    public List<Cart> findAllCarts() {
         return cartRepo.findAll();
    }

    
   public Double getCartTotal(){
    User currentUser = userService.findUserByEmail();
    System.out.println("found user");
    Cart cart = currentUser.getCart();
    return cart.getTotal();
   }

   public Double getDiscountedCartTotal(){
    User currentUser = userService.findUserByEmail();
    System.out.println("found user");
    Cart cart = currentUser.getCart();
    return cart.getDiscountedTotal();
   }


    public Cart getCartById(Integer cartId) {
        return cartRepo.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }
}