package com.HemlockStudiosWebsite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
// Tells what the table object is coming from
@Table(name = "administrator")
public class Administrator {
    // ID Lets you know its an id
    @Id
    // Column maps to the same name as the column name in the database, it is case
    // sensitive
    @Column(name = "id")
    // This will configure your id to be auto generated,
    // now you dont need to a setter for your id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username", unique=true, nullable = false)
    private String username;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "admin_id", unique=true, nullable = false)
    private String adminId;
    
    
    public Administrator() {
    }

    public String adminId() {
        return adminId;
    }

    public void adminId(String adminId) {
        this.adminId = adminId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getId() {
        return id;
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
    public String toString() {
        return "Administrator [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", adminId=" + adminId + "]";
    }


}
