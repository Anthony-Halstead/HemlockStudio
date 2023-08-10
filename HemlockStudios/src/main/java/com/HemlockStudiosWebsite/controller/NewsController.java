/**
 * The NewsController class is a REST controller that handles requests related to creating, updating,
 * deleting, and retrieving news articles.
 */
package com.HemlockStudiosWebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.CreateNewsRequest;
import com.HemlockStudiosWebsite.dto.CreateNewsResponse;
import com.HemlockStudiosWebsite.dto.DeleteResponse;
import com.HemlockStudiosWebsite.dto.UpdateNewsRequest;
import com.HemlockStudiosWebsite.entity.News;
import com.HemlockStudiosWebsite.service.NewsService;

@RestController
@RequestMapping(value="/news")
@CrossOrigin("*")
public class NewsController {
    

    @Autowired
    NewsService newsService;
    
    
  /**
   * The function `createNews` is a Java method that handles a POST request to create a news article,
   * validating the request body and returning a response entity with a success message or an error.
   * 
   * @param request The `request` parameter is an instance of the `CreateNewsRequest` class. It is
   * annotated with `@RequestBody`, which means that the method expects the request body to be
   * deserialized into an instance of `CreateNewsRequest` before being passed to the method.
   * @return The method is returning a ResponseEntity<Object>.
   */
    @RequestMapping(
        value="/createNews",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.POST
    )
    public ResponseEntity<Object> createNews(@RequestBody CreateNewsRequest request) {
        try {
            if (request.getTitle() == null || request.getDescription() == null || request.getBody() == null || request.getAnouncement() == null) {
                throw new IllegalArgumentException("Missing required fields in the request.");
            }
            System.out.println("in the create news path");
             newsService.createNews(request.getTitle(), request.getDescription(),  request.getBody(), 
             request.getAnouncement(), request.getImgUrls());
                  
             CreateNewsResponse response = new CreateNewsResponse();
              response.setMessage("news was created");
              
        
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
/**
 * The function `updateNews` is a Java method that handles a PUT request to update news information and
 * returns a response entity with a success message or an error.
 * 
 * @param request The `request` parameter is an object of type `UpdateNewsRequest`. It is annotated
 * with `@RequestBody`, which means that it will be automatically deserialized from the request body
 * JSON.
 * @return The method is returning a ResponseEntity<Object>.
 */
    @RequestMapping(
        value="/update",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.PUT
    )
    public ResponseEntity<Object> updateNews(@RequestBody UpdateNewsRequest request) {
        try {
            if (request.getId() == null || request.getTitle() == null || request.getDescription() == null || request.getBody() == null || request.getAnouncement() == null) {
                throw new IllegalArgumentException("Missing required fields in the request.");
            }
    
             newsService.updateNews(request.getId(), request.getTitle(), request.getDescription(),  request.getBody(), 
             request.getAnouncement(), request.getImgUrls());
                  
             CreateNewsResponse response = new CreateNewsResponse();
              response.setMessage("news was updated");
              
        
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
/**
 * The function `deleteNews` is a Java method that handles a DELETE request to delete a news item by
 * its ID and returns a response entity with a success message or an error message.
 * 
 * @param id The "id" parameter is the unique identifier of the news item that needs to be deleted.
 * @return The method is returning a ResponseEntity<Object>.
 */
    @RequestMapping(
        value="/delete/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> deleteNews(@PathVariable Integer id) {
        try {
            newsService.deleteNewsById(id);
    
            DeleteResponse response = new DeleteResponse();
            response.setMessage("News deleted successfully.");
    
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   
    
   /**
    * The function "findNews" returns a list of all news articles and prints a message to the console.
    * 
    * @return The method is returning a ResponseEntity object containing a list of News objects.
    */
    @GetMapping("/findAll")
        public ResponseEntity<List<News>> findNews() {
            System.out.println("in the find news endpoint");
            List<News> news = newsService.getAll();
            return ResponseEntity.ok(news); 
        }
}
