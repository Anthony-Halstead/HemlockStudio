package com.HemlockStudiosWebsite.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.HemlockStudiosWebsite.dto.UpdateUserRequest;
import com.HemlockStudiosWebsite.dto.UserAccountResponse;
import com.HemlockStudiosWebsite.dto.UserDTO;
import com.HemlockStudiosWebsite.dto.UserDetailsDTO;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.service.TokenService;
import com.HemlockStudiosWebsite.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

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

    @RequestMapping(value = "/toggleNotification", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setNotificationStatus(){
        System.out.println("In the toggle notification");
        userService.setNotificationStatus();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getNotificationStatus")
    public ResponseEntity<Boolean> getStatus(){
        Boolean notificationStatus = userService.getNotificationStatus();
        return ResponseEntity.ok(notificationStatus);
    }
}