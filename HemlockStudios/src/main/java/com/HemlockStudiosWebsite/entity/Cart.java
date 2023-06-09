package com.HemlockStudiosWebsite.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="cart")
public class Cart {
    
    @Id

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "discounted_total")
    private Double discountedTotal;

    @Column(name = "total")
    private Double total;
 
    @OneToMany(mappedBy="cart", cascade = CascadeType.REMOVE)
    private List<CartItem> itemsInCart;


    
  
   
public Cart(){

}
        public Cart(Integer id){
        super();
          this.id =id;
        }

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
    public List<CartItem> getItemsInCart() {
        return itemsInCart;
    }
    public void setItemsInCart(List<CartItem> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

   
}
