package com.HemlockStudiosWebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.Coupon;
import com.HemlockStudiosWebsite.entity.Customer;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.repo.CouponRepo;

@Service
public class CouponService {
    
    @Autowired
    CouponRepo couponRepo;
    @Autowired
    CartService cartService;
    @Autowired
    CustomerService customerService;

    public Double applyCouponDiscount(Integer cartId, String couponCode) {
        Cart cart = cartService.getCartById(cartId);
        Coupon coupon = couponRepo.findByCouponCode(couponCode);
    
        Double originalTotal = cartService.calculateCartTotal(cartId);
        Double discountValue = 0.0;
    
        // Iterate through the products in the cart
        for (Product product : cart.getItemsInCart()) {
            // If a product doesn't have a discount, apply the coupon discount
            if (product.getDiscount() == null || product.getDiscount() == 0) {
                discountValue += coupon.getDiscountValue();
            }
        }
    
        Double newTotal = originalTotal - discountValue;
    
        // Update the cart with the new total
        cart.setDiscountedTotal(newTotal);
        cartService.saveCart(cart);
    
        return newTotal;
    }
    
    public Double applySignInDiscount(Integer cartId, Integer customerId) {
        Cart cart = cartService.getCartById(cartId);
        Customer customer = customerService.getCustomerById(customerId);
    
        Double originalTotal = cartService.calculateCartTotal(cartId);
        Double discountValue = 0.0;
    
        // Apply a 5% discount if the customer is signed in
        if (customer.getIsSignedUp()) {
            discountValue += originalTotal * 0.05;
        }
    
        Double newTotal = originalTotal - discountValue;
    
        // Update the cart with the new total
        cart.setDiscountedTotal(newTotal);
        cartService.saveCart(cart);
    
        return newTotal;
    }
    public Coupon createCoupon(String couponCode) {
        Coupon coupon = new Coupon();
        coupon.setCouponCode(couponCode);
        return couponRepo.save(coupon);
    }

    public void deleteCoupon(Integer couponId) {
        if (!couponRepo.existsById(couponId)) {
            throw new RuntimeException("Coupon not found");
        }
        couponRepo.deleteById(couponId);
    }
}
