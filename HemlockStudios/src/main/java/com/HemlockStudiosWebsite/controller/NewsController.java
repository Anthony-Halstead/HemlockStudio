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
import com.HemlockStudiosWebsite.dto.DeleteRequest;
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
    
    @GetMapping("/findAll")
        public ResponseEntity<List<News>> findNews() {
            System.out.println("in the find news endpoint");
            List<News> news = newsService.getAll();
            return ResponseEntity.ok(news); // returns a 200 OK response with the newss in the body
        }
}
