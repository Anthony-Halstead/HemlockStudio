/**
 * This is a Java controller class that handles requests related to analytics data for a Hemlock
 * Studios website.
 */
package com.HemlockStudiosWebsite.controller;

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

/**
 * This function is a GET endpoint that returns the analytics data as a ResponseEntity.
 * 
 * @return The method is returning a ResponseEntity object with a generic type of AnalyticsDTO.
 */
@GetMapping("/findAll")
public ResponseEntity<AnalyticsDTO> findAnalytics() {
    System.out.println("in the find analytics endpoint");
    AnalyticsDTO analyticsDTO = analyticsService.getAnalytics();
    return ResponseEntity.ok(analyticsDTO); 
}
}
