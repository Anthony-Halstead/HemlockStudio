package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.Coupon;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.events.CouponMadeEvent;
import com.HemlockStudiosWebsite.repo.CouponRepo;

@Service
public class CouponService {
    
    @Autowired
    CouponRepo couponRepo;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ApplicationEventPublisher eventPublisher;


    public void applyCouponDiscount(String couponCode) {
        User currentUser = userService.findUserByEmail();
        Cart cart = currentUser.getCart();
        Double total = cart.getTotal();
    
        Coupon coupon = couponRepo.findByCouponCode(couponCode);
        if (coupon != null) {
            Double discount = coupon.getDiscountValue();
            Double discountedTotal = total - (total * discount);
            cart.setDiscountedTotal(discountedTotal);
            cartService.saveCart(cart);
            System.out.println("Discount applied successfully. New discounted total: " + discountedTotal);
        } else {

            System.out.println("No coupon found with the provided code");
        }
    }
    
    // public Double applySignInDiscount(Integer cartId, Integer userId) {
    //     Cart cart = cartService.getCartById(cartId);
    //     User user = userService.getUserById(userId);
    
    //     Double originalTotal = cartService.calculateCartTotal(cartId);
    //     Double discountValue = 0.0;
    
    //     // Apply a 5% discount if the user is signed in
    //     if (user.getIsSignedUp()) {
    //         discountValue += originalTotal * 0.05;
    //     }
    
    //     Double newTotal = originalTotal - discountValue;
    
    //     // Update the cart with the new total
    //     cart.setDiscountedTotal(newTotal);
    //     cartService.saveCart(cart);
    
    //     return newTotal;
    // }
    
    public Coupon createCoupon(String couponCode, Double discountValue) {
        Coupon coupon = new Coupon();
        coupon.setCouponCode(couponCode);
        coupon.setDiscountValue(discountValue);
        eventPublisher.publishEvent(new CouponMadeEvent(coupon));
        return couponRepo.save(coupon);
    }

    public void deleteCoupon(Integer couponId) {
        if (!couponRepo.existsById(couponId)) {
            throw new RuntimeException("Coupon not found");
        }
        couponRepo.deleteById(couponId);
    }

    public List<Coupon> getAll() {
        return couponRepo.findAll();
    }
}
