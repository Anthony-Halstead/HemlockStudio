package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.News;
import com.HemlockStudiosWebsite.entity.Photo;
import com.HemlockStudiosWebsite.enums.NewsEnums;
import com.HemlockStudiosWebsite.repo.NewsRepo;

@Service
public class NewsService {
    @Autowired
    NewsRepo newsRepo;

    @Autowired
    PhotoService photoService;



    public List<News> getAll()
    {
        return newsRepo.findAll();
    }

 public void createNews(String title, String description, String body, String anouncement, String[] imgUrls) {
        try {
            System.out.println("In the backend create news service start");
            News news = new News();
            news.setDescription(description);
            news.setTitle(title);
            news.setBody(body);
            NewsEnums.Anouncement anouncementEnum = NewsEnums.Anouncement.valueOf(anouncement);
        
            news.setAnouncement(anouncementEnum);
            for(String imgUrl : imgUrls)
            {
               System.out.println("Creating photo for url: " + imgUrl);
               Photo newPhoto = photoService.createPhoto(imgUrl);
               news.getPhotoReal().add(newPhoto);
            }
           newsRepo.save(news);
           System.out.println("News saved successfully");
        } catch (Exception e) {
            System.out.println("Error during news creation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public News save(News news){
         return newsRepo.save(news);
    }

    // public News updateNews(Integer id, String description, Double price, String name, String imgUrl, Double discount) {
    //     News news = newsRepo.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    
    //     if (description != null) {
    //         news.setDescription(description);
    //     }
    
    //     if (price != null) {
    //         news.setPrice(price);
    //     }
    
    //     if (name != null) {
    //         news.setName(name);
    //     }
    
    //     if (imgUrl != null) {
    //         news.setImgUrl(imgUrl);
    //     }
    
    //     if (discount != null) {
    //         news.setDiscount(discount);
    //     }
    
    //     return newsRepo.save(news);
    // }
   
    public News getNewsById(Integer id) {
        return newsRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("News not found"));

    }

  
}
