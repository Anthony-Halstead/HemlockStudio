/**
 * The EmailVerificationTokenService class is responsible for creating, finding, and deleting email
 * verification tokens for users.
 */
package com.HemlockStudiosWebsite.service;


import com.HemlockStudiosWebsite.entity.EmailVerificationToken;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.repo.EmailVerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class EmailVerificationTokenService {

    @Autowired
    EmailVerificationTokenRepo emailVerificationTokenRepo;

   /**
    * The function creates an email verification token for a user and saves it in the repository.
    * 
    * @param user The "user" parameter is an instance of the User class, which represents a user in the
    * system. It contains information about the user, such as their username, email, and password.
    * @param token The "token" parameter is a string that represents the verification token generated
    * for the user's email verification process.
    * @return The method is returning an instance of the EmailVerificationToken class.
    */
    public EmailVerificationToken createToken(User user, String token) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setUser(user);
        emailVerificationToken.setToken(token);
        emailVerificationToken.setExpiryDate(Instant.now().plus(1, ChronoUnit.DAYS));
    
        return emailVerificationTokenRepo.save(emailVerificationToken);
    }
    
    /**
     * The function findByToken searches for an EmailVerificationToken object in the
     * emailVerificationTokenRepo repository based on a given token.
     * 
     * @param token The token parameter is a string that represents the token value used to search for
     * an email verification token in the emailVerificationTokenRepo repository.
     * @return The method is returning an Optional object that contains an EmailVerificationToken.
     */
    public Optional<EmailVerificationToken> findByToken(String token) {
        return emailVerificationTokenRepo.findByToken(token);
    }

    /**
     * The function deletes an email verification token from the repository.
     * 
     * @param emailVerificationToken The emailVerificationToken parameter is an object of the
     * EmailVerificationToken class.
     */
    public void delete(EmailVerificationToken emailVerificationToken) {
        emailVerificationTokenRepo.delete(emailVerificationToken);
    }
}