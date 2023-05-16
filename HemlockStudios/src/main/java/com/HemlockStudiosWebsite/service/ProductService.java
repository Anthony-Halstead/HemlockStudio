package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

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

    public Product createProduct(String description, Double price, String name, String imgUrl, Double discount) {
        Product product = new Product();
        product.setDescription(description);
        product.setPrice(price);
        product.setName(name);
        product.setImgUrl(imgUrl);
        product.setDiscount(discount);

        if (discount != null) {
            product.setDiscount(discount);
        }

      
        Product savedProduct = productRepo.save(product);
        return savedProduct;
    }
    
    public Product save(Product product){
         return productRepo.save(product);
    }

    public Product updateProduct(Integer id, String description, Double price, String name, String imgUrl, Double discount) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    
        if (description != null) {
            product.setDescription(description);
        }
    
        if (price != null) {
            product.setPrice(price);
        }
    
        if (name != null) {
            product.setName(name);
        }
    
        if (imgUrl != null) {
            product.setImgUrl(imgUrl);
        }
    
        if (discount != null) {
            product.setDiscount(discount);
        }
    
        return productRepo.save(product);
    }
   
    public Product getProductById(Integer id) {
        return productRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    }

}
