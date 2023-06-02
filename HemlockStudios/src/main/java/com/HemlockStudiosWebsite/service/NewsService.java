package com.HemlockStudiosWebsite.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
            news.setDatePublished(LocalDate.now());
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
    

    public void updateNews(Integer id, String title, String description, String body, String anouncement, String[] imgUrls) {
        try {
            System.out.println("In the backend update News service start");
            Optional<News> optionalNews = newsRepo.findById(id);
            if (optionalNews.isPresent()) {
                News news = optionalNews.get();

            news.setDescription(description);
            news.setTitle(title);
            news.setBody(body);
            NewsEnums.Anouncement anouncementEnum = NewsEnums.Anouncement.valueOf(anouncement);
            news.setAnouncement(anouncementEnum);

                List<Photo> currentPhotos = news.getPhotoReal();
                for (Photo photo : currentPhotos) {
                    // Remove the photo from the news photo real
                    news.getPhotoReal().remove(photo);
                    // Delete the photo from the photo repository
                    photoService.deleteById(photo.getId());
                }
                
                for (String imgUrl : imgUrls) {
                    System.out.println("Creating photo for url: " + imgUrl);
                    Photo newPhoto = photoService.createPhoto(imgUrl);
                    news.getPhotoReal().add(newPhoto);
                }
                
                // Save the updated product
                newsRepo.save(news);
                System.out.println("News saved successfully");
            }
        } catch (Exception e) {
            System.out.println("Error during product creation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    


    public News save(News news){
         return newsRepo.save(news);
    }

 
    public void deleteNewsById(Integer id) {
       newsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        newsRepo.deleteById(id);
    }

    public News getNewsById(Integer id) {
        return newsRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("News not found"));
    }

  
}
