package com.HemlockStudiosWebsite.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.HemlockStudiosWebsite.dto.LoginResponseDTO;
import com.HemlockStudiosWebsite.dto.RegistrationDTO;
import com.HemlockStudiosWebsite.entity.EmailVerificationToken;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.service.AuthenticationService;
import com.HemlockStudiosWebsite.service.EmailVerificationTokenService;
import com.HemlockStudiosWebsite.service.UserService;
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
@Autowired
private AuthenticationService authenticationService;
@Autowired
UserService userService;

@Autowired
EmailVerificationTokenService emailVerificationTokenService;

@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body){
System.out.println("You are in the reg path");
User user = authenticationService.registerUser(body.getUsername(),body.getPassword(), body.getEmail());
authenticationService.sendEmailConfirmation(user);
return ResponseEntity.ok("User registered successfully");
}

@PostMapping("/registerAdmin")
public ResponseEntity<?> registerAdmin(@RequestBody RegistrationDTO body){
    System.out.println("You are in the reg path");
    authenticationService.registerAdmin(body.getUsername(),body.getPassword(), body.getEmail());
    return ResponseEntity.ok("Admin registered successfully");
    }

@GetMapping("/confirm")
public ResponseEntity<?> confirmEmail(@RequestParam String token) {
    Optional<EmailVerificationToken> optionalToken = emailVerificationTokenService.findByToken(token);
    if (!optionalToken.isPresent()) {
        return ResponseEntity.badRequest().body("Invalid token");
    }

    EmailVerificationToken validToken = optionalToken.get();
    User user = validToken.getUser();

 
        user.setEmailConfirmed(true);
        userService.save(user);
    

    emailVerificationTokenService.delete(validToken);

    return ResponseEntity.ok("Email confirmed");
}


@PostMapping("/login")
public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
    System.out.println("You are in the login path");
    String jwt = authenticationService.loginUser(body.getUsername(), body.getPassword());
    return new LoginResponseDTO(jwt);
}
}