/**
 * The AuthenticationController class is a REST controller that handles user authentication and
 * registration, email confirmation, and health check endpoints for the Hemlock Studios website.
 */
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

/**
 * The function registers a user by creating a new User object with the provided username, password,
 * and email, and sends a confirmation email.
 * 
 * @param body The "body" parameter is of type RegistrationDTO, which is a data transfer object that
 * contains the user's registration information such as username, password, and email. It is annotated
 * with @RequestBody, which means that the data will be extracted from the request body and mapped to
 * the RegistrationDTO object.
 * @return The method is returning a ResponseEntity object with a message "User registered
 * successfully".
 */
@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body){
System.out.println("You are in the reg path");
User user = authenticationService.registerUser(body.getUsername(),body.getPassword(), body.getEmail());
authenticationService.sendEmailConfirmation(user);
return ResponseEntity.ok("User registered successfully");
}

/**
 * The function registers an admin by calling the authenticationService's registerAdmin method with the
 * provided username, password, and email, and returns a ResponseEntity with a success message.
 * 
 * @param body The "body" parameter is of type RegistrationDTO, which is a data transfer object that
 * contains the information needed to register an admin. It likely includes fields such as "username",
 * "password", and "email".
 * @return The method is returning a ResponseEntity object with a message "Admin registered
 * successfully".
 */
@PostMapping("/registerAdmin")
public ResponseEntity<?> registerAdmin(@RequestBody RegistrationDTO body){
    System.out.println("You are in the reg path");
    authenticationService.registerAdmin(body.getUsername(),body.getPassword(), body.getEmail());
    return ResponseEntity.ok("Admin registered successfully");
    }


/**
 * The function confirms the user's email by validating the token, updating the user's email
 * confirmation status, and deleting the token.
 * 
 * @param token The `token` parameter is a string that represents the email verification token. This
 * token is used to verify the user's email address and confirm their email.
 * @return The method is returning a ResponseEntity object.
 */
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

/**
 * The loginUser function is a POST request handler that takes in a RegistrationDTO object as the
 * request body, logs the user in, and returns a LoginResponseDTO object containing a JWT token.
 * 
 * @param body The `body` parameter is of type `RegistrationDTO` and it represents the request body of
 * the POST request. It contains the username and password entered by the user during the login
 * process.
 * @return The method is returning a LoginResponseDTO object.
 */
@PostMapping("/login")
public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
    System.out.println("You are in the login path");
    String jwt = authenticationService.loginUser(body.getUsername(), body.getPassword());
    return new LoginResponseDTO(jwt);
}

/**
 * The function returns a ResponseEntity with a "Healthy" message for a health check endpoint.
 * 
 * @return The method is returning a ResponseEntity object with a String body containing the text
 * "Healthy".
 */
@GetMapping("/health")
public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("Healthy");
}
}