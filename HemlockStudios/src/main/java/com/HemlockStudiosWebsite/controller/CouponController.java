package com.HemlockStudiosWebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.CreateCouponRequest;
import com.HemlockStudiosWebsite.dto.CreateCouponResponse;
import com.HemlockStudiosWebsite.dto.DeleteRequest;
import com.HemlockStudiosWebsite.dto.DeleteResponse;
import com.HemlockStudiosWebsite.dto.DiscountRequest;
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
    value="/deleteCoupon",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> deleteCoupon(@RequestBody DeleteRequest deleteCouponRequest) {
    try {
        couponService.deleteCoupon(deleteCouponRequest.getId());

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
public ResponseEntity<Object> applyCouponDiscount(@RequestBody DiscountRequest discountRequest) {
    try {
        Double newTotal = couponService.applyCouponDiscount(discountRequest.getCartId(), discountRequest.getCouponCode());
        return new ResponseEntity<>(newTotal, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
}
