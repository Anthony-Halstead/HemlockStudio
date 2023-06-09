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

import com.HemlockStudiosWebsite.dto.CreateProductRequest;
import com.HemlockStudiosWebsite.dto.CreateProductResponse;
import com.HemlockStudiosWebsite.dto.DeleteRequest;
import com.HemlockStudiosWebsite.dto.DeleteResponse;
import com.HemlockStudiosWebsite.dto.UpdateProductRequest;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.service.PhotoService;
import com.HemlockStudiosWebsite.service.ProductService;

@RestController
@RequestMapping(value="/product")
@CrossOrigin("*")
public class ProductController  {
    
@Autowired
ProductService productService;

	


@RequestMapping(
    value="/createProduct",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.POST
)
public ResponseEntity<Object> createProduct(@RequestBody CreateProductRequest request) {
    try {
        if (request.getDescription() == null || request.getPrice() == null || request.getName() == null || request.getImgUrls() == null) {
            throw new IllegalArgumentException("Missing required fields in the request.");
        }

         productService.createProduct(request.getDescription(), request.getPrice(),  request.getName(), 
         request.getImgUrls(), request.getCategory(), request.getSubcategory(), request.getSize());
              
         CreateProductResponse response = new CreateProductResponse();
          response.setMessage("product was created");
          
    
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@RequestMapping(
    value = "/update",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE,
    method = RequestMethod.PUT
)
public ResponseEntity<Object> updateProduct(@RequestBody UpdateProductRequest request) {
    try {
        if (request.getId() == null || request.getDescription() == null || request.getPrice() == null || request.getName() == null || request.getImgUrls() == null) {
            throw new IllegalArgumentException("Missing required field: id");
        }
System.out.println("in the update product path");
       productService.updateProduct(
            request.getId(),
            request.getDescription(), request.getPrice(),  request.getName(), 
            request.getImgUrls(), request.getCategory(), request.getSubcategory(), request.getSize(), request.getDiscount()
        );

        CreateProductResponse response = new CreateProductResponse();
response.setMessage("Product was updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}




@RequestMapping(
    value="/delete/{id}",
    method = RequestMethod.DELETE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
    try {
        System.out.println("in the delete product endpoint"+id);
        productService.deleteProductById(id);

        DeleteResponse response = new DeleteResponse();
        response.setMessage("Product deleted successfully.");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    } catch (Error e) {
        return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}   


@GetMapping("/findAll")
    public ResponseEntity<List<Product>> findProducts() {
        System.out.println("in the find products endpoint");
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products); 
    }
}

