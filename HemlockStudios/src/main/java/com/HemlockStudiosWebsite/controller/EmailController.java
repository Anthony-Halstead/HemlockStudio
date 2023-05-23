package com.HemlockStudiosWebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.EmailRequest;
import com.HemlockStudiosWebsite.dto.HtmlEmailRequest;
import com.HemlockStudiosWebsite.service.EmailSenderService;

@RestController
@RequestMapping(value="/email")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    EmailSenderService emailSenderService;
    
@PostMapping("/send-email")
public ResponseEntity<Object> sendEmail(@RequestBody EmailRequest emailMessage){

emailSenderService.sendEmail(emailMessage.getTo(),emailMessage.getSubject(), emailMessage.getMessage());
return ResponseEntity.ok("success");
}

// @PostMapping("/send-htmlemail")
// public ResponseEntity<Object> sendHTMLEmail(@RequestBody HtmlEmailRequest emailMessage){

// emailSenderService.sendHtmlEmail(emailMessage.getTo());
// return ResponseEntity.ok("success");
// }



}