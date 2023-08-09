package com.HemlockStudiosWebsite.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.News;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.events.NewsMadeEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
@Autowired
JavaMailSender mailSender;
@Autowired
UserService userService;

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
}