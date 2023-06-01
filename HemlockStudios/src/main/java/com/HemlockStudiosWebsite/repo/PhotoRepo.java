package com.HemlockStudiosWebsite.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HemlockStudiosWebsite.entity.Photo;

public interface PhotoRepo extends JpaRepository<Photo, Integer>{
	
    @Query(value="select * from photo where product_ID = ?1", nativeQuery = true) 
    public List<Photo> findByProductID(Integer productID);

}
