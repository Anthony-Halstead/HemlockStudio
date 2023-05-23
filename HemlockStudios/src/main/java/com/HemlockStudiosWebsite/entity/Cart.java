package com.HemlockStudiosWebsite.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name="cart")
public class Cart {
    
    @Id

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "discounted_total")
    private Double discountedTotal;


    @ManyToMany
    @JoinTable(
        name="cart_products",
        joinColumns=
        @JoinColumn(name="cart_id", referencedColumnName="id"), 
        inverseJoinColumns=
        @JoinColumn(name="products_id", referencedColumnName="id")
    )
    private List<Product> itemsInCart;

public Double getDiscountedTotal() {
    return discountedTotal;
}

public void setDiscountedTotal(Double discountedTotal) {
    this.discountedTotal = discountedTotal;
}
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<Product> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }
}
