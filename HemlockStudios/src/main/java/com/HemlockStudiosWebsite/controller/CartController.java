package com.HemlockStudiosWebsite.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.HemlockStudiosWebsite.dto.CartItemDTO;
import com.HemlockStudiosWebsite.dto.ChangeItemInCartRequest;
import com.HemlockStudiosWebsite.dto.ChangeItemInCartResponse;
import com.HemlockStudiosWebsite.entity.CartItem;
import com.HemlockStudiosWebsite.service.CartItemService;
import com.HemlockStudiosWebsite.service.CartService;


@RestController
@RequestMapping(value="/cart")
@CrossOrigin("*")
public class CartController {
    @Autowired
    CartItemService cartItemService;
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
           
            System.out.println("in the add item to cart path with the product id"+request.getProductId());
            cartItemService.addItemToCart(request.getProductId());
            ChangeItemInCartResponse response = new ChangeItemInCartResponse();
            response.setMessage("Item Added to Cart Successfully.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        }catch(Error e){
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findItemsInCart")
    public ResponseEntity<List<CartItemDTO>> findItemsInCart() {
        System.out.println("in the find coupons endpoint");
        List<CartItem> cartItems = cartItemService.getItemsInCart();
    
        // Convert CartItem entities into CartItemDTOs
        List<CartItemDTO> cartItemDTOs = cartItems.stream().map(cartItem -> {
            CartItemDTO dto = new CartItemDTO();
            dto.setProduct(cartItem.getProduct());
            dto.setQuantity(cartItem.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    
        return ResponseEntity.ok(cartItemDTOs); 
    }



@RequestMapping(
    value="/removeItemFromCart/{productId}",
    method = RequestMethod.DELETE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> removeItemFromCart(@PathVariable Integer productId) {
    try {
        System.out.println("in the use item in cart Path");
        cartItemService.removeItemFromCart(productId);

        ChangeItemInCartResponse response = new ChangeItemInCartResponse();
        response.setMessage("Item Removed from Cart Successfully.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/total")
public ResponseEntity<Double> getCartTotal() {
    Double totalPrice = cartService.getCartTotal();
    return ResponseEntity.ok(totalPrice);
}

@GetMapping("/discountedTotal")
public ResponseEntity<Double> getDiscountedCartTotal() {
    Double discountedTotalPrice = cartService.getDiscountedCartTotal();
    return ResponseEntity.ok(discountedTotalPrice);
}


@RequestMapping(
    value="/makePurchase",
    method = RequestMethod.PUT,
    produces = MediaType.APPLICATION_JSON_VALUE
)public ResponseEntity<Object> makePurchase() {
    try {
        System.out.println("in the make purchase Path");
        cartItemService.makePurchase();
        ChangeItemInCartResponse response = new ChangeItemInCartResponse();
        response.setMessage("Purchase Success.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
