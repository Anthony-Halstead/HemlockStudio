package com.HemlockStudiosWebsite.service;

import java.util.List;
import java.util.Optional;

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

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public void deleteProductById(Integer id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cartProductService.removeProductFromAllCarts(product);
        productRepo.deleteById(id);
    }

    public void createProduct(String description, Double price, String name, String[] imgUrls, String category,
            String subcategory, String size) {
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
            for (String imgUrl : imgUrls) {
                System.out.println("Creating photo for url: " + imgUrl);
                Photo newPhoto = photoService.createPhoto(imgUrl);
                product.getPhotoAlbum().add(newPhoto);
            }
            productRepo.save(product);
            System.out.println("Product saved successfully");
        } catch (Exception e) {
            System.out.println("Error during product creation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void updateProduct(Integer id, String description, Double price, String name, String[] imgUrls,
            String category, String subcategory, String size, Double discount) {
        try {
            System.out.println("In the backend create product service start");
            Optional<Product> optionalProduct = productRepo.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setDescription(description);
                product.setPrice(price);
                product.setName(name);
                product.setDiscount(discount);
                ProductEnums.Category categoryEnum = ProductEnums.Category.valueOf(category);
                ProductEnums.Subcategory subcategoryEnum = ProductEnums.Subcategory.valueOf(subcategory);
                ProductEnums.Size sizeEnum = ProductEnums.Size.valueOf(size);
                product.setCategory(categoryEnum);
                product.setSubcategory(subcategoryEnum);
                product.setSize(sizeEnum);
                List<Photo> currentPhotos = product.getPhotoAlbum();
                for (Photo photo : currentPhotos) {
                    // Remove the photo from the product photo album
                    product.getPhotoAlbum().remove(photo);
                    // Delete the photo from the photo repository
                    photoService.deleteById(photo.getId());
                }
                
                for (String imgUrl : imgUrls) {
                    System.out.println("Creating photo for url: " + imgUrl);
                    Photo newPhoto = photoService.createPhoto(imgUrl);
                    product.getPhotoAlbum().add(newPhoto);
                }
                
                // Save the updated product
                productRepo.save(product);
                System.out.println("Product saved successfully");
            }
        } catch (Exception e) {
            System.out.println("Error during product creation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Product getProductById(Integer id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    }

}
