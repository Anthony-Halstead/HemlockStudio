package com.HemlockStudiosWebsite.dto;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class UserDetailsDTO {
   

    private String username;
    private Integer id;
    private String email;
    private Set<? extends GrantedAuthority> roles;

    public UserDetailsDTO(String username, Integer id, String email, Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.roles = new HashSet<>(roles);
    }
        // Getters and setters for all the fields
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
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
        public Set<? extends GrantedAuthority> getRoles() {
            return roles;
        }
        public void setRoles(Set<? extends GrantedAuthority> roles) {
            this.roles = roles;
        }

       
     
    
    }

