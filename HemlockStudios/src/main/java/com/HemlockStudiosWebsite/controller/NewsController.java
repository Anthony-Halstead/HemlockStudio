package com.HemlockStudiosWebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.CreateNewsRequest;
import com.HemlockStudiosWebsite.dto.CreateNewsResponse;
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
    
    
    // @RequestMapping(
    //     value="/delete",
    //     method = RequestMethod.DELETE,
    //     consumes = MediaType.APPLICATION_JSON_VALUE,
    //     produces = MediaType.APPLICATION_JSON_VALUE
    // )
    // public ResponseEntity<Object> deleteNews(@RequestBody DeleteRequest request) {
    //     try {
    //         newsService.deleteNewsById(request.getId());
    
    //         DeleteResponse response = new DeleteResponse();
    //         response.setMessage("News deleted successfully.");
    
    //         return new ResponseEntity<Object>(response, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    //     } catch (Error e) {
    //         return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }   
    
    // @GetMapping("/findAll")
    //     public ResponseEntity<List<News>> findNewss() {
    //         System.out.println("in the find newss endpoint");
    //         List<News> newss = newsService.getAll();
    //         return ResponseEntity.ok(newss); // returns a 200 OK response with the newss in the body
    //     }
}
