/**
 * The EmailController class is a REST controller that handles email sending requests for a Hemlock
 * Studios website.
 */
package com.HemlockStudiosWebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.ContactEmailRequest;
import com.HemlockStudiosWebsite.dto.EmailRequest;

import com.HemlockStudiosWebsite.service.EmailSenderService;

@RestController
@RequestMapping(value="/email")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    EmailSenderService emailSenderService;
    
/**
 * This Java function sends an email using the provided email message details.
 * 
 * @param emailMessage The emailMessage parameter is an instance of the EmailRequest class. It contains
 * the details of the email to be sent, such as the recipient's email address, the subject of the
 * email, and the message content.
 * @return The method is returning a ResponseEntity object with the response body set to "success".
 */
@PostMapping("/send-email")
public ResponseEntity<Object> sendEmail(@RequestBody EmailRequest emailMessage){

emailSenderService.sendEmail(emailMessage.getTo(),emailMessage.getSubject(), emailMessage.getMessage());
return ResponseEntity.ok("success");
}

/**
 * This function handles a POST request to send a contact email with the provided email, subject, and
 * message.
 * 
 * @param emailMessage The parameter `emailMessage` is of type `ContactEmailRequest`, which is a custom
 * class that represents the request body of the API endpoint. It contains the following properties:
 * @return The method is returning a ResponseEntity object with the body "success".
 */
@PostMapping("/contact-email")
public ResponseEntity<Object> contactEmail(@RequestBody ContactEmailRequest emailMessage){

emailSenderService.contactEmail(emailMessage.getEmail(),emailMessage.getSubject(), emailMessage.getMessage());
return ResponseEntity.ok("success");
}
}