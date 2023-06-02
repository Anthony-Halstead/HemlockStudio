package com.HemlockStudiosWebsite.controller;
import com.HemlockStudiosWebsite.dto.EnumResponseDTO;
import com.HemlockStudiosWebsite.enums.NewsEnums;
import com.HemlockStudiosWebsite.enums.ProductEnums;
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

    public EnumsController(EnumService enumService) {
        this.enumService = enumService;
    }

    @GetMapping("/findAll")
    public EnumResponseDTO getAllEnums() {
        EnumResponseDTO response = new EnumResponseDTO();
        response.setCategories(enumService.getEnumValuesAsString(ProductEnums.Category.values()));
        response.setSubcategories(enumService.getEnumValuesAsString(ProductEnums.Subcategory.values()));
        response.setSizes(enumService.getEnumValuesAsString(ProductEnums.Size.values()));
        response.setAnouncements(enumService.getEnumValuesAsString(NewsEnums.Anouncement.values()));
        return response;
    }
}