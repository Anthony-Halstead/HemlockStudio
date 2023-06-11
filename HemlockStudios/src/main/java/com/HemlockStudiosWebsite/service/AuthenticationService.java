package com.HemlockStudiosWebsite.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.EmailVerificationToken;
import com.HemlockStudiosWebsite.entity.Role;
import com.HemlockStudiosWebsite.entity.User;

import com.HemlockStudiosWebsite.repo.RoleRepo;
import com.HemlockStudiosWebsite.repo.UserRepo;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class AuthenticationService {
@Autowired
private UserRepo userRepo;
@Autowired
private RoleRepo roleRepo;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private AuthenticationManager authenticationManager;
@Autowired
private TokenService tokenService;
@Autowired
EmailVerificationTokenService emailVerificationTokenService;
@Autowired
EmailSenderService emailSenderService;
@Autowired
CartService cartService;


public User registerUser(String username, String password, String email){
    String encodedPassword = passwordEncoder.encode(password);
    Role userRole = roleRepo.findByAuthority("USER").get();
    Set<Role> authorities = new HashSet<>();
    authorities.add(userRole);

    User user = new User(0, username, encodedPassword, email, authorities);
    user.setEmailConfirmed(false);

    Cart cart = cartService.createCart();
    user.setCart(cart);

    return userRepo.save(user);
}

public User registerAdmin(String username, String password, String email){
    String encodedPassword = passwordEncoder.encode(password);
    Role adminRole = roleRepo.findByAuthority("ADMIN").get();
    Role userRole = roleRepo.findByAuthority("USER").get();
    Set<Role> authorities = new HashSet<>();
    authorities.add(adminRole);
    authorities.add(userRole);

    User user = new User(0, username, encodedPassword, email, authorities);
    user.setEmailConfirmed(true);
    user.setNotificationsEnabled(true);
   

    return userRepo.save(user);
}


public void sendEmailConfirmation(User user) {
    // create token
    String token = UUID.randomUUID().toString();

    // save token
    EmailVerificationToken emailVerificationToken = emailVerificationTokenService.createToken(user, token);
    
    // send confirmation email
    String confirmUrl = "http://localhost:8080/auth/confirm?token=" + emailVerificationToken.getToken();
    emailSenderService.sendEmail(user.getEmail(), "Confirm your email", "Click this link to confirm your email: " + confirmUrl);
}


public String loginUser(String username, String password){
    try{
        System.out.println("You are in the login user method");
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = userRepo.findByUsername(username).get();

        if (!user.getIsEmailConfirmed()) {
            throw new RuntimeException("Email not confirmed check your email and click on the link");
        }

        return tokenService.generateJwt(auth);
    }catch(AuthenticationException e){
        System.out.println(e.getMessage());
        throw e; // Re-throwing the exception to let the controller handle it.
    }
}


}