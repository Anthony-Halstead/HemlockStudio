package com.HemlockStudiosWebsite.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Photo;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.enums.ProductEnums;
import com.HemlockStudiosWebsite.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PhotoService photoService;

    @Autowired
    CartProductService cartProductService;


    public List<Product> getAll()
    {
        return productRepo.findAll();
    }

    public void deleteProductById(Integer id) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        cartProductService.removeProductFromAllCarts(product);
        productRepo.deleteById(id);
    }

    public void createProduct(String description, Double price, String name, String[] imgUrls,  String category,  String subcategory,  String size) {
        try {
            System.out.println("In the backend create product service start");
            Product product = new Product();
            product.setDescription(description);
            product.setPrice(price);
            product.setName(name);
            ProductEnums.Category categoryEnum = ProductEnums.Category.valueOf(category);
            ProductEnums.Subcategory subcategoryEnum = ProductEnums.Subcategory.valueOf(subcategory);
            ProductEnums.Size sizeEnum = ProductEnums.Size.valueOf(size);
        
            product.setCategory(categoryEnum);
            product.setSubcategory(subcategoryEnum);
            product.setSize(sizeEnum);
            for(String imgUrl : imgUrls)
            {
               System.out.println("Creating photo for url: " + imgUrl);
               Photo newPhoto = photoService.createPhoto(imgUrl);
               product.getPhotoAlbum().add(newPhoto);
            }
            System.out.println("In the backend create product service two");
           productRepo.save(product);
           System.out.println("Product saved successfully");
        } catch (Exception e) {
            System.out.println("Error during product creation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Product save(Product product){
         return productRepo.save(product);
    }

    // public Product updateProduct(Integer id, String description, Double price, String name, String imgUrl, Double discount) {
    //     Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    
    //     if (description != null) {
    //         product.setDescription(description);
    //     }
    
    //     if (price != null) {
    //         product.setPrice(price);
    //     }
    
    //     if (name != null) {
    //         product.setName(name);
    //     }
    
    //     if (imgUrl != null) {
    //         product.setImgUrl(imgUrl);
    //     }
    
    //     if (discount != null) {
    //         product.setDiscount(discount);
    //     }
    
    //     return productRepo.save(product);
    // }
   
    public Product getProductById(Integer id) {
        return productRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    }

}
