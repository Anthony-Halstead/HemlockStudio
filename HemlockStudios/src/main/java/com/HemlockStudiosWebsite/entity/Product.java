package com.HemlockStudiosWebsite.entity;

import java.util.ArrayList;
import java.util.List;

import com.HemlockStudiosWebsite.enums.ProductEnums;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
  
    @Id
    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "price", scale = 2)
    private Double price;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)     
    @JoinColumn(name="product_Id")
    private List<Photo> photoAlbum = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductEnums.Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "subcategory")
    private ProductEnums.Subcategory subcategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private ProductEnums.Size size;


    @Column(name="discount")
    private Double discount;


    public ProductEnums.Category getCategory() {
        return category;
    }

    public void setCategory(ProductEnums.Category category) {
        this.category = category;
    }

    public ProductEnums.Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ProductEnums.Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public ProductEnums.Size getSize() {
        return size;
    }

    public void setSize(ProductEnums.Size size) {
        this.size = size;
    }
    public List<Photo> getPhotoAlbum() {
        return photoAlbum;
    }

    public void setPhotoAlbum(List<Photo> photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
