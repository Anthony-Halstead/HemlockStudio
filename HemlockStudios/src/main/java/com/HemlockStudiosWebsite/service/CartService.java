package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.repo.CartRepo;


@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductService productService;

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

    public void addItemToCart(Integer cartId, Integer productId) {
        Cart cart = cartRepo.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found"));

    Product product = productService.getProductById(productId);
//
// update total prices here to send back prices of all products stored in cart
//
//
    cart.getItemsInCart().add(product);
    cartRepo.save(cart);
    }

    public void removeItemFromCart(Integer cartId, Integer productId) {
        Cart cart = cartRepo.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productService.getProductById(productId);
//
// update total prices here to send back prices of all products stored in cart
//
//
        cart.getItemsInCart().remove(product);
    cartRepo.save(cart);
    }

    public Double calculateCartTotal(Integer cartId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    
        List<Product> products = cart.getItemsInCart();
    
        Double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public Cart getCartById(Integer cartId) {
        return cartRepo.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }
}