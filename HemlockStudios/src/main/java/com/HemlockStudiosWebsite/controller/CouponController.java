package com.HemlockStudiosWebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.ApplyCouponDiscountRequest;
import com.HemlockStudiosWebsite.dto.CreateCouponRequest;
import com.HemlockStudiosWebsite.dto.CreateCouponResponse;
import com.HemlockStudiosWebsite.dto.DeleteRequest;
import com.HemlockStudiosWebsite.dto.DeleteResponse;
import com.HemlockStudiosWebsite.dto.DiscountRequest;
import com.HemlockStudiosWebsite.entity.Coupon;
import com.HemlockStudiosWebsite.service.CouponService;

@RestController
@RequestMapping(value="/coupon")
@CrossOrigin("*")
public class CouponController {

@Autowired
CouponService couponService;


@RequestMapping(
    value="/createCoupon",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> createCoupon(@RequestBody CreateCouponRequest request) {
    try {
        couponService.createCoupon(request.getCouponCode(), request.getDiscountValue());

        CreateCouponResponse response = new CreateCouponResponse ();
        response.setMessage("Coupon created Successfully.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

@RequestMapping(
    value="/delete/{id}",
    method = RequestMethod.DELETE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> deleteCoupon(@PathVariable Integer id) {
    try {
        couponService.deleteCoupon(id);

        DeleteResponse response = new DeleteResponse();
        response.setMessage("Coupon deleted successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

@RequestMapping(
    value="/applyCouponDiscount",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> applyCouponDiscount(@RequestBody ApplyCouponDiscountRequest request) {
    try {
        couponService.applyCouponDiscount(request.getCouponCode());
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/findAll")
    public ResponseEntity<List<Coupon>> findCoupons() {
        System.out.println("in the find coupons endpoint");
        List<Coupon> coupons = couponService.getAll();
        return ResponseEntity.ok(coupons); 
    }
}
