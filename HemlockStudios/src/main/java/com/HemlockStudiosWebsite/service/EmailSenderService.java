/**
 * The EmailSenderService class is responsible for sending emails to users, including notifications
 * about news updates from Hemlock Studios.
 */
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

 /**
  * The function sends an email with the specified recipient, subject, and message using a mail sender.
  * 
  * @param to The "to" parameter is the email address of the recipient to whom the email will be sent.
  * @param subject The subject parameter is a String that represents the subject of the email. It is
  * typically a brief summary or title that describes the content of the email.
  * @param message The "message" parameter is a string that represents the content of the email message
  * that you want to send. It can include any text or HTML content that you want to include in the
  * email.
  */
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("anthony.dan.hal@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

/**
 * The function sends an email with a specified subject and message to a specified email address.
 * 
 * @param email The email parameter is the email address of the sender.
 * @param subject The subject of the email that you want to send. It should be a String value.
 * @param message The "message" parameter is a string that represents the content of the email message
 * that you want to send. It can include any text or information that you want to include in the email.
 */
public void contactEmail(String email, String subject, String message){
 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo("anthony.dan.hal@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

 
   
   // The `@Async` annotation is used to indicate that a method should be executed asynchronously. When
   // a method is annotated with `@Async`, it will be executed in a separate thread, allowing the
   // calling thread to continue its execution without waiting for the asynchronous method to complete.
   // This can be useful for methods that perform time-consuming tasks, such as sending emails, as it
   // allows the application to continue processing other tasks while the email is being sent in the
   // background.
    @Async
    @EventListener
    // The `handleNewsMadeEvent` method is an event listener that listens for a `NewsMadeEvent` event.
    // When this event is triggered, the method calls the `sendNewsCreatedHtmlEmail` method and passes
    // the `News` object associated with the event.
    public void handleNewsMadeEvent(NewsMadeEvent event) {
        sendNewsCreatedHtmlEmail(event.getNewsMade());
    }
    
   /**
    * The function sends an HTML email to all users who have notifications enabled, containing
    * information about a newly created news item.
    * 
    * @param createdNews The parameter "createdNews" is an object of type "News" that represents the
    * news article that was created.
    */
    public void sendNewsCreatedHtmlEmail(News createdNews) {
        List<User> users = userService.getAll();
        for (User user : users) {
            if (user.getNotificationsEnabled() == true) {
                sendNewsEmail(user.getEmail(), createdNews);
            }
        }
    }
    
   /**
    * The function sends an email with news content to a specified recipient using Java's MimeMessage
    * and MimeMessageHelper classes.
    * 
    * @param to The email address of the recipient to whom the news email will be sent.
    * @param createdNews The `createdNews` parameter is an object of the `News` class. It contains
    * information about a news article, such as its title, announcement, and description.
    */
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