/**
 * The EnumsController class is a REST controller that handles requests related to enums and returns a
 * response containing the values of the NewsEnums.Anouncement enum.
 */
package com.HemlockStudiosWebsite.controller;
import com.HemlockStudiosWebsite.dto.EnumResponseDTO;
import com.HemlockStudiosWebsite.enums.NewsEnums;
import com.HemlockStudiosWebsite.service.EnumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
@CrossOrigin("*")
public class EnumsController {

    @Autowired
    EnumService enumService;

// The code `public EnumsController(EnumService enumService)` is a constructor for the
// `EnumsController` class. It takes an instance of `EnumService` as a parameter and assigns it to
// the `enumService` field of the class. This allows the `EnumsController` to have access to the
// methods and functionality provided by the `EnumService` class.
//     public EnumsController(EnumService enumService) {
//         this.enumService = enumService;
//     }

  /**
   * The function returns all the values of the NewsEnums.Anouncement enum as a string array in an
   * EnumResponseDTO object.
   * 
   * @return The method is returning an instance of the EnumResponseDTO class.
   */
    @GetMapping("/findAll")
    public EnumResponseDTO getAllEnums() {
        EnumResponseDTO response = new EnumResponseDTO();
        response.setAnouncements(enumService.getEnumValuesAsString(NewsEnums.Anouncement.values()));
        return response;
    }
}