package com.HemlockStudiosWebsite.events;

import com.HemlockStudiosWebsite.entity.Coupon;

public class CouponMadeEvent {
    private final Coupon couponMade;

    public CouponMadeEvent(Coupon couponMade) {
        this.couponMade = couponMade;
    }

    public Coupon getCouponMade() {
        return couponMade;
    }
}
