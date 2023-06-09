package com.HemlockStudiosWebsite.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.CreditCardRequest;
import com.HemlockStudiosWebsite.dto.CreditCardResponse;
import com.HemlockStudiosWebsite.dto.GetUserCartTotalRequest;
import com.HemlockStudiosWebsite.dto.LoginResponseDTO;
import com.HemlockStudiosWebsite.dto.RemoveCreditCardRequest;
import com.HemlockStudiosWebsite.dto.UpdateCreditCardRequest;
import com.HemlockStudiosWebsite.dto.UpdateFavoritesRequest;
import com.HemlockStudiosWebsite.dto.UpdateFavoritesResponse;
import com.HemlockStudiosWebsite.dto.UpdateUserRequest;
import com.HemlockStudiosWebsite.dto.UserAccountResponse;
import com.HemlockStudiosWebsite.dto.UserDTO;
import com.HemlockStudiosWebsite.dto.UserDetailsDTO;
import com.HemlockStudiosWebsite.entity.CreditCard;
import com.HemlockStudiosWebsite.entity.Product;
// import com.RealEstateHomes.entity.Car;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.service.CartService;
import com.HemlockStudiosWebsite.service.CouponService;
import com.HemlockStudiosWebsite.service.CreditCardService;
import com.HemlockStudiosWebsite.service.ProductService;
import com.HemlockStudiosWebsite.service.TokenService;
import com.HemlockStudiosWebsite.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    CouponService couponService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/findUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> findUserById(@PathVariable Integer id) {
        try {
            User foundUser = userService.findById(id);
            return new ResponseEntity<Object>(foundUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUser")
public ResponseEntity<Object> currentUser() {
    try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaim("email");

        // assuming you have a UserService with a method to fetch user by username
        User user = userService.findByEmail(email);

        UserDetailsDTO userDetails = new UserDetailsDTO(user.getUsername(), user.getId(), user.getEmail(), user.getAuthorities());
        return new ResponseEntity<Object>(userDetails, HttpStatus.OK);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @RequestMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findUsers() {
        List<User> users = userService.getAll();
        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getUsername(),
                        user.isAccountNonExpired(),
                        user.isAccountNonLocked(),
                        user.isCredentialsNonExpired()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserRequest request) {

        try {
            if (request.getId() == null || request.getUsername() == null || request.getEmail() == null) {
                throw new IllegalArgumentException("Missing required fields in the request.");
            }
           userService.updateUser(request.getId(), request.getUsername(), request.getEmail());

            UserAccountResponse response = new UserAccountResponse();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addProductToFavorites", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Object> addProductToFavorites(@RequestBody UpdateFavoritesRequest request) {
        try {
            if (request.getProductId() == null) {
                throw new IllegalArgumentException("Missing required fields in the request.");
            }
            productService.addProductToFavorites(request.getProductId());

            UpdateFavoritesResponse response = new UpdateFavoritesResponse();
            response.setMessage("Item Added to Favorites Successfully.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/removeProductFromFavorites/{favoriteProductId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeFavorite(@PathVariable Integer favoriteProductId) {
        try {
          

            System.out.println("REMOVE PRODUCT FAVORITE PATH");
            productService.removeProductFromFavorites(favoriteProductId);

            UpdateFavoritesResponse response = new UpdateFavoritesResponse();
            response.setMessage("Item Removed from Favorites Successfully.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/addCreditCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    // get favorites for display
    public ResponseEntity<Object> addCreditCard(@RequestBody CreditCardRequest request) {
        try {
            System.out.println("in the add credit card path");
            if (request.getCardNumber() == null || request.getExpirationMonth() == null
                    ||
                    request.getExpirationYear() == null || request.getCardHolderName() == null
                    || request.getCvv() == null) {
                throw new IllegalArgumentException("Missing required fields in the request.");
            }
            userService.addCreditCard(request.getCardNumber(), request.getExpirationMonth(),
                    request.getExpirationYear(), request.getCardHolderName(), request.getCvv());

            CreditCardResponse response = new CreditCardResponse();
            response.setMessage("Credit Card Added to User Successfully.");

            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/removeCreditCard/{creditCardId}")
    public ResponseEntity<Object> removeCreditCard(@PathVariable Integer creditCardId)  {
        try {
            
    System.out.println("In the removeCreditCard path (BackEnd)");
            userService.removeCreditCard(creditCardId);
    
            CreditCardResponse response = new CreditCardResponse();
            response.setMessage("Credit Card removed Successfully.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updateCreditCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCreditCard(@RequestBody UpdateCreditCardRequest request) {

        try {
            if (request.getCreditCardId() == null || request.getCardNumber() == null || request.getExpirationMonth() == null
            ||
            request.getExpirationYear() == null || request.getCardHolderName() == null
            || request.getCvv() == null) {
        throw new IllegalArgumentException("Missing required fields in the request.");
    }
    creditCardService.updateCreditCard(request.getCreditCardId(), request.getCardNumber(), request.getExpirationMonth(),
            request.getExpirationYear(), request.getCardHolderName(), request.getCvv());

    CreditCardResponse response = new CreditCardResponse();
    response.setMessage("Credit Card Updated Successfully.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
    @GetMapping("/findCreditCards")
    public ResponseEntity<List<CreditCard>> findCreditCards() {
        System.out.println("In the find credit cards path");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Long userIdLong = (Long) jwt.getClaim("userId");
        System.out.println("CREDITCARD does the id exist?" + userIdLong);
        List<CreditCard> creditCards = userService.getCreditCards(userIdLong.intValue());
        return ResponseEntity.ok(creditCards); 
    }
    
    @GetMapping("/findFavoriteProducts")
    public ResponseEntity<List<Product>> findFavoriteProducts() {
        System.out.println("In the find favorites path");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Long userIdLong = (Long) jwt.getClaim("userId");
        List<Product> favorites = userService.getFavoriteProducts(userIdLong.intValue());
        return ResponseEntity.ok(favorites); 
    }
    

    // @RequestMapping(value = "/user/cartTotal", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Double> getUserCartTotal(@RequestBody GetUserCartTotalRequest request) {
    //     if (request.getUserId() == null || request.getCartId() == null) {
    //         throw new IllegalArgumentException("Missing required fields in the request.");
    //     }

    //     Integer userId = request.getUserId();
    //     Integer cartId = request.getCartId();
    //     User user = userService.getUserById(userId);
    //     Double totalPrice = cartService.calculateCartTotal(cartId);

    //     // Apply the sign-in discount if the user is signed up
    //     if (user.getIsSignedUp()) {
    //         totalPrice = couponService.applySignInDiscount(cartId, userId);
    //     }

    //     return ResponseEntity.ok(totalPrice);
    // }

}