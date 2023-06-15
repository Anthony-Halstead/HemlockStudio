package com.HemlockStudiosWebsite.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.dto.ProductSaleInfoDTO;
import com.HemlockStudiosWebsite.entity.Coupon;
import com.HemlockStudiosWebsite.entity.News;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.events.CouponMadeEvent;
import com.HemlockStudiosWebsite.events.NewsMadeEvent;
import com.HemlockStudiosWebsite.events.ProductMadeEvent;
import com.HemlockStudiosWebsite.events.ReceiptEvent;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
//Inject Java Mail Sender
@Autowired
JavaMailSender mailSender;
@Autowired
UserService userService;
@Autowired
ProductService productService;

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("anthony.dan.hal@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }

    public void contactEmail(String email, String subject, String message){
 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo("anthony.dan.hal@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }



    @Async
    @EventListener
    public void handleReceiptEvent(ReceiptEvent event) {
        sendReceiptHtmlEmail(event.getProductSales(), event.getUserEmail());
    }

public void sendReceiptHtmlEmail( List<ProductSaleInfoDTO> productSales, String to)
{
    try{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Double totalPrice = 0.0;
        DecimalFormat priceFormat = new DecimalFormat("#0.00");
        
        String htmlContent = "<h1>| Please see below for receipt |</h1>";
        for (ProductSaleInfoDTO sale : productSales) {
            Product currentProduct = productService.getProductById(sale.getProductId());
            Double currentPrice = currentProduct.getPrice();
            String formattedPrice = priceFormat.format(currentPrice);
        
            htmlContent += "<p>Product: " + sale.getProductId() + " x" + sale.getQuantity() + " price: $" + formattedPrice + "</p>";
            totalPrice += currentPrice;
        }
        
        String formattedTotalPrice = priceFormat.format(totalPrice);
        htmlContent += "<p> Your TOTAL: $" + formattedTotalPrice + "</p>";
        htmlContent += "<p>Thank you for shopping at Hemlock Studios!</p>";

        helper.setTo(to);
        helper.setSubject("Hemlock Studios Receipt");
        helper.setText(htmlContent, true);
        mailSender.send(message);
    } catch(MessagingException e) {
        throw new MailParseException(e);
    }
}

    @Async
    @EventListener
    public void handleNewsMadeEvent(NewsMadeEvent event) {
        sendNewsCreatedHtmlEmail(event.getNewsMade());
    }
    
    public void sendNewsCreatedHtmlEmail(News createdNews) {
        List<User> users = userService.getAll();
        for (User user : users) {
            if (user.getNotificationsEnabled() == true) {
                sendNewsEmail(user.getEmail(), createdNews);
            }
        }
    }
    
    public void sendNewsEmail(String to, News createdNews) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
    
            String htmlContent = "<h1>Update from Hemlock Studios</h1>";
            htmlContent += "<p>" + createdNews.getTitle() + "</p>"
            + "<p>" + createdNews.getAnouncement() + "</p>"
            + "<p>" + createdNews.getDescription() + "</p>"
            + "<p>Visit HemlockStudios.com for the full article!</p>";
    
            helper.setTo(to);
            helper.setSubject("New update from Hemlock Studios!");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch(MessagingException e) {
            throw new MailParseException(e);
        }
    }



    @Async
    @EventListener
    public void handleProductMadeEvent(ProductMadeEvent event) {
        sendProductCreatedHtmlEmail(event.getProductMade());
    }
    
    public void sendProductCreatedHtmlEmail(Product createdProduct) {
        List<User> users = userService.getAll();
        for (User user : users) {
            if (user.getNotificationsEnabled() == true) {
                sendProductEmail(user.getEmail(), createdProduct);
            }
        }
    }
    
    public void sendProductEmail(String to, Product createdProduct) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
    
            String htmlContent = "<h1>New Product Available</h1>";
            htmlContent += "<p>"+ createdProduct.getName() +"</p>";
            if (!createdProduct.getPhotoAlbum().isEmpty()) {
                String photoUrl = createdProduct.getPhotoAlbum().get(0).getPhotoUrl();
                htmlContent += "<img src='" + photoUrl + "' alt='Product Image'>";
            }
    
            helper.setTo(to);
            helper.setSubject("New Product: " + createdProduct.getName());
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch(MessagingException e) {
            throw new MailParseException(e);
        }
    }
   
    
    
    @Async
    @EventListener
    public void handleCouponMadeEvent(CouponMadeEvent event) {
        sendCouponCreatedHtmlEmail(event.getCouponMade());
    }
    
    public void sendCouponCreatedHtmlEmail(Coupon createdCoupon) {
        List<User> users = userService.getAll();
        for (User user : users) {
            if (user.getNotificationsEnabled() == true) {
                sendCouponEmail(user.getEmail(), createdCoupon);
            }
        }
    }
    
    public void sendCouponEmail(String to, Coupon createdCoupon) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String discountFormatted = new DecimalFormat("#0").format(createdCoupon.getDiscountValue() * 100);
            String discountPercentage = "%" + discountFormatted;
            
            String htmlContent = "<h1>Thank you for being a Fan!</h1>";
            htmlContent += "<p>Use this coupon code today: [" + createdCoupon.getCouponCode() + "] and get " + discountPercentage
                    + " off your next purchase!</p>";
            
            helper.setTo(to);
            helper.setSubject("Get this free coupon for being a member!");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch(MessagingException e) {
            throw new MailParseException(e);
        }
    }
    
    
}