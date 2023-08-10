/**
 * The User class represents a user entity with properties such as email, username, password, and
 * authorities.
 */
package com.HemlockStudiosWebsite.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
// The `@Table(name="user")` annotation is used to specify the name of the database table that
// corresponds to the `User` entity. In this case, the table name is set to "user".
@Table(name="user")
public class User implements UserDetails{
   // The code snippet `@Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
   // private Integer id;` is used to define the primary key of the `User` entity in the database.
    @Id
    @Column(name = "id")
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;

// The `@Column(name = "email", unique = true, nullable = false)` annotation is used to specify the
// mapping of the `email` property to a column in the database table.
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
   // The `@Column(name = "username", unique = true, nullable = false)` annotation is used to specify
   // the mapping of the `username` property to a column in the database table.
    @Column(name = "username", unique = true,  nullable = false)
    private String username;

    // The `@Column(name = "password", nullable = false)` annotation is used to specify the mapping of
    // the `password` property to a column in the database table.
    @Column(name = "password", nullable = false)
    private String password;
  
  // The `@Column(name = "email_confirmed", nullable = false)` annotation is used to specify the
  // mapping of the `emailConfirmed` property to a column in the database table.
    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

     // The `@Column(name = "is_signed_up", nullable = false)` annotation is used to specify the
     // mapping of the `isSignedUp` property to a column in the database table.
     @Column(name = "is_signed_up", nullable = false)
    private Boolean isSignedUp = false;

    // The `@Column(name = "notifications_enabled", nullable = false)` annotation is used to specify
    // the mapping of the `notificationsEnabled` property to a column in the database table.
    @Column(name = "notifications_enabled", nullable = false)
    private Boolean notificationsEnabled = false;

   // The `@ManyToMany` annotation is used to define a many-to-many relationship between the `User`
   // entity and the `Role` entity.
   // The `fetch=FetchType.EAGER` annotation is used to specify the fetching strategy for
    // the `authorities` property in the `User` entity.
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    name="user_role_junction",
    joinColumns = {@JoinColumn(name="user_id")},
    inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;
// The `public User() { super(); this.authorities = new HashSet<Role>(); }` is a constructor for the
// User class.

    public User() {
        super();
        this.authorities = new HashSet<Role>();
        }
    
     // The `public User(Integer id, String username, String password, String email, Set<Role>
     // authorities)` is a constructor for the `User` class. It allows you to create a new `User`
     // object with the specified values for the `id`, `username`, `password`, `email`, and
     // `authorities` properties. The `super()` call is used to invoke the constructor of the
     // superclass (in this case, `Object`). The `this` keyword is used to refer to the current object
     // (i.e., the `User` object being created).
        public User(Integer id, String username, String password, String email, Set<Role> authorities){
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        }



    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public Boolean getIsSignedUp() {
        return isSignedUp;
    }

    public void setIsSignedUp(Boolean isSignedUp) {
        this.isSignedUp = isSignedUp;
    }

    public boolean getIsEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /**
    * The function returns a collection of granted authorities.
    * 
    * @return The method is returning a collection of objects that implement the GrantedAuthority
    * interface.
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
    }
    public void setAuthorities(Set<Role> authorities){
    this.authorities = authorities;
    }
    /**
     * The function returns the username.
     * 
     * @return The method is returning the value of the instance variable "username".
     */
    @Override
    public String getUsername() {
    return this.username;
    }
    public void setUsername(String username)
    {
    this.username = username;
    }
    /**
     * The function isAccountNonExpired() returns true, indicating that the account is not expired.
     * 
     * @return The method is returning a boolean value of true.
     */
    @Override
    public boolean isAccountNonExpired() {
    return true;
    }
   /**
    * The function isAccountNonLocked() returns true, indicating that the account is not locked.
    * 
    * @return The method is returning a boolean value of true.
    */
    @Override
    public boolean isAccountNonLocked() {
    return true;
    }
    /**
     * The function isCredentialsNonExpired() returns true, indicating that the user's credentials are
     * not expired.
     * 
     * @return The method is returning a boolean value of true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
    return true;
    }
    /**
     * The function returns true to indicate that the feature is enabled.
     * 
     * @return The method is returning a boolean value of true.
     */
    @Override
    public boolean isEnabled() {
    return true;
    }
    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }
    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
}