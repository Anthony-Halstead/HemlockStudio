package com.HemlockStudiosWebsite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.dto.ProductSaleInfoDTO;
import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.CartItem;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.events.PurchaseMadeEvent;
import com.HemlockStudiosWebsite.events.ReceiptEvent;
import com.HemlockStudiosWebsite.events.TotalEvent;
import com.HemlockStudiosWebsite.repo.CartItemRepo;
import com.HemlockStudiosWebsite.repo.CartRepo;

@Service
public class CartItemService {

    @Autowired
    UserService userService;
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    ApplicationEventPublisher eventPublisher;


    public List<CartItem> getItemsInCart() {
        User currentUser = userService.findUserByEmail();
        Cart cart = currentUser.getCart();
        List<CartItem> cartItems = cart.getItemsInCart();
        return cartItems;
    }

    public void addItemToCart(Integer productId) {
        System.out.println("in the add item to cart service path");
        User currentUser = userService.findUserByEmail();
        System.out.println("found user");
        Cart cart = currentUser.getCart();
        System.out.println("found cart");
        Product product = productService.getProductById(productId);
        System.out.println("found product");
      
        CartItem existingCartItem = cartItemRepo.findByCartIdAndProductId(cart.getId(), product.getId());
        System.out.println("returned from the cart item repo");
        if (existingCartItem != null) {
         
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepo.save(existingCartItem);
        } else {
         
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(1);
            cartItemRepo.save(newCartItem);
        }
        updateCartTotal(cart);
        cartService.saveCart(cart);
    }

 private void updateCartTotal(Cart cart) {
    double total = 0.0;
    for (CartItem item : cart.getItemsInCart()) {
        total += item.getProduct().getPrice() * item.getQuantity();
    }
    cart.setTotal(total);
}


public void removeItemFromCart(Integer productId) {
    User currentUser = userService.findUserByEmail();
    Cart cart = currentUser.getCart();
    Product product = productService.getProductById(productId);
    // Find the cart item for the product
    CartItem item = cartItemRepo.findByCartIdAndProductId(cart.getId(), product.getId());
    if (item != null) {
        if (item.getQuantity() > 1) {
            // If more than one item, decrement the quantity
            item.setQuantity(item.getQuantity() - 1);
            cartItemRepo.save(item);
        } else {
            // If only one item, remove it
            cart.getItemsInCart().remove(item);
            cartItemRepo.delete(item);
        }
    }
    // Recalculate the total
    updateCartTotal(cart);
    cartService.saveCart(cart);
}

public void makePurchase() {
    User currentUser = userService.findUserByEmail();
    Cart cart = currentUser.getCart();
    List<CartItem> cartItems = cart.getItemsInCart();
    String userEmail = currentUser.getEmail();

    Double total;
    
    if(cart.getTotal() == cart.getDiscountedTotal())
    {
        total = cart.getTotal();
    }else total = cart.getDiscountedTotal();

    eventPublisher.publishEvent(new TotalEvent(total));

    List<ProductSaleInfoDTO> productSales = new ArrayList<>();
    for (CartItem item : cartItems) {
        productSales.add(new ProductSaleInfoDTO(item.getProduct().getId(), item.getQuantity()));
    }

    eventPublisher.publishEvent(new PurchaseMadeEvent(productSales));
    eventPublisher.publishEvent(new ReceiptEvent(userEmail, productSales));

    cart.getItemsInCart().clear();
    updateCartTotal(cart);
    cartService.saveCart(cart);
}



}
