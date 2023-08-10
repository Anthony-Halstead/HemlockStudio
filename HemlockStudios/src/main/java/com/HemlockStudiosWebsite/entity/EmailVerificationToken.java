/**
 * The EmailVerificationToken class represents an email verification token associated with a user
 * account.
 */
package com.HemlockStudiosWebsite.entity;
import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
// The `@Table(name = "email_verification_token")` annotation is used to specify the name of the
// database table that will be mapped to the `EmailVerificationToken` entity. In this case, it
// indicates that the entity will be mapped to a table named "email_verification_token" in the
// database.
@Table(name = "email_verification_token")
public class EmailVerificationToken {

   // The `@Id` annotation is used to indicate that the `id` field is the primary key of the entity. It
   // marks the field as the unique identifier for each instance of the `EmailVerificationToken` class.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    // The `@OneToOne` annotation is used to specify a one-to-one relationship between the
    // `EmailVerificationToken` entity and the `User` entity.
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

  // The line `private Instant expiryDate;` declares a private instance variable named `expiryDate` of
  // type `Instant`. This variable is used to store the expiration date of the email verification
  // token. The `Instant` class in Java represents a point in time, and it is commonly used for
  // handling dates and times in Java applications.
    private Instant expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}