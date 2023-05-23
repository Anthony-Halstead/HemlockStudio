package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
//Inject Java Mail Sender
@Autowired
JavaMailSender mailSender;


    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("anthony.dan.hal@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }

    // public void sendHtmlEmail(String to) {
    //     try{
    //     MimeMessage message = mailSender.createMimeMessage();
    //     MimeMessageHelper helper = new MimeMessageHelper(message, true);

    //    List<Property> propertiesInInventory = propertyService.findPropertiesInInventory();
        

    //     String htmlContent = "<h1>List of properties</h1>";

    //     for (Property property : propertiesInInventory) {
          
    //         htmlContent += "<p>"+property+"</p>";
    //         if (!property.getPropertyPhotos().isEmpty()) {
    //             htmlContent += "<img src='" + property.getPropertyPhotos().get(0).getPhotoUrl() + "' alt='Property photo'>";
    //         }
    //     }

      
    //     helper.setTo(to);
    //     helper.setSubject("Properties in inventory");
    //     helper.setText(htmlContent, true);
    //     mailSender.send(message);
    // }catch(MessagingException e){throw new MailParseException(e);}
      
    
    // }
}