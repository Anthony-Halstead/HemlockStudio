package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HemlockStudiosWebsite.entity.News;

@Repository
public interface NewsRepo extends JpaRepository<News, Integer>{
    
}
