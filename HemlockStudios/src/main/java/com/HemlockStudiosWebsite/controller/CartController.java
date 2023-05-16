package com.HemlockStudiosWebsite.controller;

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

import com.HemlockStudiosWebsite.dto.ChangeItemInCartRequest;
import com.HemlockStudiosWebsite.dto.ChangeItemInCartResponse;
import com.HemlockStudiosWebsite.service.CartService;

@RestController
@RequestMapping(value="/cart")
@CrossOrigin("*")
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping(
    value="/addItemToCart",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> addItemToCart(@RequestBody ChangeItemInCartRequest request) {
    try {
        if(request.getCartId()==null||request.getProductId()==null){
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        cartService.addItemToCart(request.getCartId(), request.getProductId());

        ChangeItemInCartResponse response = new ChangeItemInCartResponse();
        response.setMessage("Item Added to Cart Successfully.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@RequestMapping(
    value="/removeItemFromCart",
    method = RequestMethod.DELETE,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> removeItemFromCart(@RequestBody ChangeItemInCartRequest request) {
    try {
        cartService.removeItemFromCart(request.getCartId(), request.getProductId());

      
        ChangeItemInCartResponse response = new ChangeItemInCartResponse();
        response.setMessage("Item Removed from Cart Successfully.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/total/{cartId}")
public ResponseEntity<Double> getCartTotal(@PathVariable Integer cartId) {
    Double totalPrice = cartService.calculateCartTotal(cartId);
    return ResponseEntity.ok(totalPrice);
}
}
