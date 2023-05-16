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

import com.HemlockStudiosWebsite.dto.CreditCardRequest;
import com.HemlockStudiosWebsite.dto.CreditCardResponse;
import com.HemlockStudiosWebsite.dto.CustomerAccountRequest;
import com.HemlockStudiosWebsite.dto.CustomerAccountResponse;
import com.HemlockStudiosWebsite.dto.DeleteRequest;
import com.HemlockStudiosWebsite.dto.DeleteResponse;
import com.HemlockStudiosWebsite.dto.GetCustomerCartTotalRequest;
import com.HemlockStudiosWebsite.dto.RemoveCreditCardRequest;
import com.HemlockStudiosWebsite.dto.UpdateCustomerRequest;
import com.HemlockStudiosWebsite.dto.UpdateFavoritesRequest;
import com.HemlockStudiosWebsite.dto.UpdateFavoritesResponse;
import com.HemlockStudiosWebsite.entity.Customer;
import com.HemlockStudiosWebsite.service.CartService;
import com.HemlockStudiosWebsite.service.CouponService;
import com.HemlockStudiosWebsite.service.CustomerService;

@RestController
@RequestMapping(value="/customer")
@CrossOrigin("*")
public class CustomerController  {
    
@Autowired
CustomerService customerService;
@Autowired
CartService cartService;
@Autowired
CouponService couponService;

@RequestMapping(
    value="/signUpAsCustomer",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)
public ResponseEntity<Object> signUpAsCustomer(@RequestBody CustomerAccountRequest request) {
    try {
        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }

        Customer savedCustomer = customerService.signUpAsCustomer(request.getUsername(), request.getEmail(), request.getPassword(), true);
               
        CustomerAccountResponse response = new CustomerAccountResponse();
        response.setId(savedCustomer.getId());
        response.setUsername(savedCustomer.getUsername());
        response.setEmail(savedCustomer.getEmail());
        response.setIsSignedUp(savedCustomer.getIsSignedUp());
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@RequestMapping(
    value="/deleteCustomer",
    method = RequestMethod.DELETE,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> deleteCustomer(@RequestBody DeleteRequest request) {
    try {
        customerService.deleteCustomerById(request.getId());

        DeleteResponse response = new DeleteResponse();
        response.setMessage("Customer deleted successfully.");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}   
   
@RequestMapping(
    value="/signInAsCustomer",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)

public ResponseEntity<Object> signInAsCustomer(@RequestBody CustomerAccountRequest request){

    try{
        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        Customer savedCustomer = customerService.signInAsCustomer(request.getUsername(), request.getEmail(), request.getPassword());
     
        
        CustomerAccountResponse response = new CustomerAccountResponse();
        response.setId(savedCustomer.getId());
        response.setUsername(savedCustomer.getUsername());
        response.setEmail(savedCustomer.getEmail());
        response.setCart(savedCustomer.getCart());

       return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

@RequestMapping(
    value="/updateCustomer",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.PUT
)
public ResponseEntity<Object> updateCustomer(@RequestBody UpdateCustomerRequest request){

    try{
        if (request.getId() == null || request.getUsername() == null || request.getEmail() == null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        Customer updatedCustomer = customerService.updateCustomer(request.getId(), request.getUsername(), request.getEmail());
               
        CustomerAccountResponse response = new CustomerAccountResponse();
        response.setId(updatedCustomer.getId());
        response.setUsername(updatedCustomer.getUsername());
        response.setEmail(updatedCustomer.getEmail());

       return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

@RequestMapping(
    value="/addProductToFavorites",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)
public ResponseEntity<Object> addProductToFavorites(@RequestBody UpdateFavoritesRequest request){
    try {
    if(request.getCustomerId()==null||request.getProductId()==null){
        throw new IllegalArgumentException("Missing required fields in the request.");
    }
    customerService.addProductToFavorites(request.getCustomerId(), request.getProductId());

    
    UpdateFavoritesResponse response = new UpdateFavoritesResponse();
    response.setMessage("Item Added to Favorites Successfully.");
    return new ResponseEntity<Object>(response, HttpStatus.OK);
}catch(Exception e){
    return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
}catch(Error e){
    return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
}

}

@RequestMapping(
    value="/removeProductFromFavorites",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.DELETE
)
public ResponseEntity<Object> removeProductFromFavorites(@RequestBody UpdateFavoritesRequest request){
    try {
    if(request.getCustomerId()==null||request.getProductId()==null){
        throw new IllegalArgumentException("Missing required fields in the request.");
    }
    customerService.removeProductFromFavorites(request.getCustomerId(), request.getProductId());

    
    UpdateFavoritesResponse response = new UpdateFavoritesResponse();
    response.setMessage("Item Removed from Favorites Successfully.");
    return new ResponseEntity<Object>(response, HttpStatus.OK);
}catch(Exception e){
    return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
}catch(Error e){
    return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
}

}

@RequestMapping(
    value="/addCreditCardToCustomer",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)
//get favorites for display
public ResponseEntity<Object> addCreditCardToCustomer(@RequestBody CreditCardRequest request){
    try {
        if(request.getCustomerId()==null||request.getCardNumber()==null||request.getExpirationMonth()==null||
        request.getExpirationYear()==null||request.getCardHolderName()==null||request.getCvv()==null){
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        customerService.addCreditCardToCustomer(request.getCustomerId(), request.getCardNumber(), request.getExpirationMonth(), 
        request.getExpirationYear(), request.getCardHolderName(), request.getCvv());
    
        
       CreditCardResponse response = new CreditCardResponse();
        response.setMessage("Credit Card Added to Customer Successfully.");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    }

@RequestMapping(
    value="/removeCreditCardFromCustomer",
    method = RequestMethod.DELETE,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> removeCreditCardFromCustomer(@RequestBody RemoveCreditCardRequest request) {
    try {
        if(request.getCustomerId()==null||request.getCreditCardId()==null){
            throw new IllegalArgumentException("Missing required fields in the request.");
        }
        customerService.removeCreditCardFromCustomer(request.getCustomerId(), request.getCreditCardId());
    
        
       CreditCardResponse response = new CreditCardResponse();
        response.setMessage("Credit Card removed Successfully.");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }catch(Error e){
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//getcreditcards for display

@RequestMapping(
    value="/customer/cartTotal",
    method = RequestMethod.GET,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Double> getCustomerCartTotal(@RequestBody GetCustomerCartTotalRequest request) {
    if(request.getCustomerId()==null||request.getCartId()==null){
        throw new IllegalArgumentException("Missing required fields in the request.");
    }
    
    Integer customerId = request.getCustomerId();
    Integer cartId = request.getCartId();
    Customer customer = customerService.getCustomerById(customerId);
    Double totalPrice = cartService.calculateCartTotal(cartId);

    // Apply the sign-in discount if the customer is signed up
    if (customer.getIsSignedUp()) {
        totalPrice = couponService.applySignInDiscount(cartId, customerId);
    }

    return ResponseEntity.ok(totalPrice);
}

}


