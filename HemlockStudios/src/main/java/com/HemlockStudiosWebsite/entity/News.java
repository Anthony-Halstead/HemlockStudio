package com.HemlockStudiosWebsite.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.HemlockStudiosWebsite.enums.NewsEnums;

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
@Table(name="news")
public class News {
    
    @Id
    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "date_published")
    private LocalDate datePublished;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "anouncement")
    private NewsEnums.Anouncement anouncement;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "body")
    private String body;

    @OneToMany(cascade = CascadeType.ALL)     
    @JoinColumn(name="product_Id")
    private List<Photo> photoReal = new ArrayList<>();

    public Integer getId() {
        return id;
    }

  

    public LocalDate getDateAdded() {
        return datePublished;
    }

    public void setDateAdded(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

 
    public LocalDate getDatePublished() {
        return datePublished;
    }



    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }



    public NewsEnums.Anouncement getAnouncement() {
        return anouncement;
    }



    public void setAnouncement(NewsEnums.Anouncement anouncement) {
        this.anouncement = anouncement;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Photo> getPhotoReal() {
        return photoReal;
    }

    public void setPhotoReal(List<Photo> photoReal) {
        this.photoReal = photoReal;
    }
    

    
}
