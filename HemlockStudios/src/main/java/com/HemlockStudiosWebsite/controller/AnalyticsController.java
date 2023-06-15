package com.HemlockStudiosWebsite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HemlockStudiosWebsite.dto.AnalyticsDTO;
import com.HemlockStudiosWebsite.service.AnalyticsService;

@RestController
@RequestMapping(value="/analytics")
@CrossOrigin("*")
public class AnalyticsController {
    @Autowired
    AnalyticsService analyticsService;

@GetMapping("/findAll")
public ResponseEntity<AnalyticsDTO> findAnalytics() {
    System.out.println("in the find analytics endpoint");
    AnalyticsDTO analyticsDTO = analyticsService.getAnalytics();
    return ResponseEntity.ok(analyticsDTO); 
}


}
