package com.HemlockStudiosWebsite.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


// Annotate Objects with Entity, lets project know its a Object from the DB
@Entity
// Tells what table the object is coming from
@Table(name="user")
public class User implements UserDetails{
    // ID lets you know its an id
    @Id
    // Column maps to the same name as the column name in the database, it is case sensitive
    @Column(name = "id")
// This will configure your id to be auto generated, now you don't need a setter for your id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "username", unique = true,  nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
  
    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

     @Column(name = "is_signed_up", nullable = false)
    private Boolean isSignedUp = false;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

@ManyToMany
    @JoinTable(
        name = "user_favorite_products",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> favoriteProducts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<CreditCard> wallet;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    name="user_role_junction",
    joinColumns = {@JoinColumn(name="user_id")},
    inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;


   
    public User() {
        super();
        this.authorities = new HashSet<Role>();
        }
    
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(List<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public List<CreditCard> getWallet() {
        return wallet;
    }

    public void setWallet(List<CreditCard> wallet) {
        this.wallet = wallet;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
    }
    public void setAuthorities(Set<Role> authorities){
    this.authorities = authorities;
    }
    @Override
    public String getUsername() {
    return this.username;
    }
    public void setUsername(String username)
    {
    this.username = username;
    }
    @Override
    public boolean isAccountNonExpired() {
    return true;
    }
    @Override
    public boolean isAccountNonLocked() {
    return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
    return true;
    }
    @Override
    public boolean isEnabled() {
    return true;
    }

}