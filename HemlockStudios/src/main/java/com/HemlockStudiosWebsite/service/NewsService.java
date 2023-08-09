package com.HemlockStudiosWebsite.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.News;
import com.HemlockStudiosWebsite.entity.Photo;
import com.HemlockStudiosWebsite.enums.NewsEnums;
import com.HemlockStudiosWebsite.events.NewsMadeEvent;
import com.HemlockStudiosWebsite.repo.NewsRepo;

@Service
public class NewsService {
    @Autowired
    NewsRepo newsRepo;

    @Autowired
    PhotoService photoService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

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
            eventPublisher.publishEvent(new NewsMadeEvent(news));
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

            if (imgUrls != null) {
                for (String imgUrl : imgUrls) {
                    if (imgUrl != null && !photoExistsInList(news.getPhotoReal(), imgUrl)) {
                        System.out.println("Creating photo for url: " + imgUrl);
                        Photo newPhoto = photoService.createPhoto(imgUrl);
                        news.getPhotoReal().add(newPhoto);
                    }
                }
            }

            // Save the updated news
            newsRepo.save(news);
            System.out.println("News updated successfully");
        }
    } catch (Exception e) {
        System.out.println("Error during news update: " + e.getMessage());
        e.printStackTrace();
    }
}

private boolean photoExistsInList(List<Photo> photoList, String imgUrl) {
    for (Photo photo : photoList) {
        if (photo.getPhotoUrl().equals(imgUrl)) {
            return true;
        }
    }
    return false;
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
