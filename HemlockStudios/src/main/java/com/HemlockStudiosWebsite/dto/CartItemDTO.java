package com.HemlockStudiosWebsite.dto;

import com.HemlockStudiosWebsite.entity.Product;

public class CartItemDTO {

    private Product product;
    private Integer quantity;
  

    public CartItemDTO() {
    }

    public CartItemDTO(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   

}