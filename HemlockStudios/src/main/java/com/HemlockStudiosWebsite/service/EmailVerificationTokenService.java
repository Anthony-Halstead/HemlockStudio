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

    public EmailVerificationToken createToken(User user, String token) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setUser(user);
        emailVerificationToken.setToken(token);
        emailVerificationToken.setExpiryDate(Instant.now().plus(1, ChronoUnit.DAYS));
    
        return emailVerificationTokenRepo.save(emailVerificationToken);
    }
    
    public Optional<EmailVerificationToken> findByToken(String token) {
        return emailVerificationTokenRepo.findByToken(token);
    }

    public void delete(EmailVerificationToken emailVerificationToken) {
        emailVerificationTokenRepo.delete(emailVerificationToken);
    }
}