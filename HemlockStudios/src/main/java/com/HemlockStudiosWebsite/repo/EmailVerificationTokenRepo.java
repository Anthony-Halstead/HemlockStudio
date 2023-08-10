// This code is defining a repository interface for the `EmailVerificationToken` entity in a Spring
// Boot application.
package com.HemlockStudiosWebsite.repo;

import com.HemlockStudiosWebsite.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepo extends JpaRepository<EmailVerificationToken, Long> {
    /**
     * The function findByToken searches for an EmailVerificationToken object based on a given token.
     * 
     * @param token A string representing the token used for email verification.
     * @return The method is returning an Optional object that contains an EmailVerificationToken.
     */
    Optional<EmailVerificationToken> findByToken(String token);
}