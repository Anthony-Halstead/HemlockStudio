/**
 * The Photo class is an entity class representing a photo with an ID and a URL.
 */
package com.HemlockStudiosWebsite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
// The `@Table(name="Photo")` annotation is used to specify the name of the database table that will be
// mapped to the `Photo` entity class. In this case, it specifies that the table name should be
// "Photo".
@Table(name="Photo")

public class Photo {
    
// The `@Id` annotation is used to specify that the annotated field is the primary key of the entity.
// In this case, it indicates that the `id` field in the `Photo` class is the primary key.
@Id

@Column(name = "id")

// The `@GeneratedValue(strategy = GenerationType.IDENTITY)` annotation is used to specify the
// generation strategy for the primary key of the entity. In this case, it indicates that the primary
// key value will be automatically generated by the database using an identity column.
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

// The `@Column` annotation is used to specify the mapping of a persistent property or field to a
// column in the database table. In this case, it is used to map the `photoUrl` field of the `Photo`
// class to a column named "photoUrl" in the database table.
@Column(name = "photoUrl", columnDefinition = "LONGTEXT", unique = true)
String photoUrl;

public Photo() {
}

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public String getPhotoUrl() {
    return photoUrl;
}

public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
}
}