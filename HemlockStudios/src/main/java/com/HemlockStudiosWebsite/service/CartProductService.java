package com.HemlockStudiosWebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.repo.CartRepo;

@Service
public class CartProductService {

    @Autowired
    private CartRepo cartRepo;

    public void removeProductFromAllCarts(Product product) {
        cartRepo.findAll().forEach(cart -> {
            if (cart.getItemsInCart().contains(product)) {
                cart.getItemsInCart().remove(product);
                cartRepo.save(cart);
            }
        });
    }
}