/**
 * The NewsService class is responsible for handling CRUD operations for the News entity in the Hemlock
 * Studios website.
 */
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

    /**
     * The function returns all news items from the news repository.
     * 
     * @return The method is returning a List of News objects.
     */
    public List<News> getAll()
    {
        return newsRepo.findAll();
    }

/**
 * The function creates a news object with the given title, description, body, announcement, and image
 * URLs, and saves it to the news repository.
 * 
 * @param title The title of the news article.
 * @param description The description parameter is a String that represents the description of the news
 * article. It provides a brief summary or overview of the news content.
 * @param body The body parameter is a string that represents the main content or text of the news
 * article. It typically includes the details, information, or story that the news article is
 * conveying.
 * @param anouncement The "anouncement" parameter is a string that represents the type of announcement
 * for the news. It is converted to an enum value using the `valueOf` method of the
 * `NewsEnums.Anouncement` enum.
 * @param imgUrls The `imgUrls` parameter is an array of strings that represents the URLs of the images
 * associated with the news article.
 */
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
    

/**
 * The function updates a news article with the provided id, title, description, body, announcement,
 * and image URLs.
 * 
 * @param id The unique identifier of the news item that needs to be updated.
 * @param title The title of the news article.
 * @param description The description of the news article.
 * @param body The "body" parameter is a string that represents the main content or body of the news
 * article. It typically contains the detailed information or story that the news article is conveying.
 * @param anouncement The "anouncement" parameter is a string that represents the announcement type for
 * the news. It is converted to an enum value using the `NewsEnums.Anouncement.valueOf(anouncement)`
 * method.
 * @param imgUrls The `imgUrls` parameter is an array of strings that represents the URLs of the images
 * associated with the news.
 */
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

           
            newsRepo.save(news);
            System.out.println("News updated successfully");
        }
    } catch (Exception e) {
        System.out.println("Error during news update: " + e.getMessage());
        e.printStackTrace();
    }
}

/**
 * The function checks if a given image URL exists in a list of photos.
 * 
 * @param photoList A list of Photo objects.
 * @param imgUrl The imgUrl parameter is a String that represents the URL of a photo.
 * @return The method is returning a boolean value. It returns true if the photo with the specified
 * imgUrl exists in the photoList, and false otherwise.
 */
private boolean photoExistsInList(List<Photo> photoList, String imgUrl) {
    for (Photo photo : photoList) {
        if (photo.getPhotoUrl().equals(imgUrl)) {
            return true;
        }
    }
    return false;
}
  /**
   * The function saves a news object to the news repository.
   * 
   * @param news The "news" parameter is an object of the News class that represents the news article
   * to be saved.
   * @return The method is returning a News object.
   */
    public News save(News news){
         return newsRepo.save(news);
    }

 
  /**
   * The function deletes a news item from the repository based on its ID.
   * 
   * @param id The id parameter is an Integer representing the unique identifier of the news item that
   * needs to be deleted.
   */
    public void deleteNewsById(Integer id) {
       newsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        newsRepo.deleteById(id);
    }

 /**
  * The function retrieves a news object from the news repository based on its ID, or throws an
  * exception if the news is not found.
  * 
  * @param id The id parameter is an Integer representing the unique identifier of the news item that
  * we want to retrieve.
  * @return The method is returning a News object.
  */
    public News getNewsById(Integer id) {
        return newsRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("News not found"));
    }

  
}
