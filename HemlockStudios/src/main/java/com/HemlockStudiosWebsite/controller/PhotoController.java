/**
 * The PhotoController class is a RESTful controller that handles CRUD operations for the Photo entity
 * in a Hemlock Studios website.
 */
package com.HemlockStudiosWebsite.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.entity.Photo;
import com.HemlockStudiosWebsite.service.PhotoService;


@RestController
@RequestMapping(value="/photo")
@CrossOrigin("*")

public class PhotoController {
  
    @Autowired
    PhotoService photoService;


   /**
    * This function handles a POST request to create a new photo object and returns the saved photo or
    * an error response.
    * 
    * @param photo The "photo" parameter is of type "Photo" and is annotated with "@RequestBody". This
    * means that the parameter will be populated with the JSON data from the request body. The JSON
    * data will be deserialized into a "Photo" object.
    * @return The method is returning a ResponseEntity<Object>.
    */
    @RequestMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<Object> create(@RequestBody Photo photo) {

        try {
            Photo savedPhoto = photoService.save(photo);
            return new ResponseEntity<Object>(savedPhoto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /**
    * The function `findAll` is a GET request handler that retrieves all photos and returns them as a
    * JSON response.
    * 
    * @return The method is returning a ResponseEntity object.
    */
    @RequestMapping(
        value="/findAll",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET
    )
    public ResponseEntity<Object> findAll() {

        try {
            List<Photo> allPhoto = photoService.findAll();
            return new ResponseEntity<Object>(allPhoto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * The function `updatePhoto` is a POST request handler that updates a photo and returns the
     * updated photo object or an error message.
     * 
     * @param photo The "photo" parameter is of type Photo and is annotated with @RequestBody, which
     * means it will be deserialized from the request body.
     * @return The method is returning a ResponseEntity<Object>.
     */
    @RequestMapping(
        value="/updatePhoto",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.POST
    )
    public ResponseEntity<Object> updatePhoto(@RequestBody Photo photo) {

        try {
            Photo updatePhoto = photoService.update(photo);
            return new ResponseEntity<Object>(updatePhoto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

   /**
    * The function `deletePhoto` is a Java method that handles a DELETE request to delete a photo by
    * its ID, and returns an appropriate response entity.
    * 
    * @param id The "id" parameter is the identifier of the photo that needs to be deleted.
    * @return The deletePhoto method returns a ResponseEntity<Object>.
    */
    @RequestMapping(
        value="/deletePhoto/{id}",
        method = RequestMethod.DELETE
    )
    public ResponseEntity<Object> deletePhoto(@PathVariable Integer id) {

        try {
            photoService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}