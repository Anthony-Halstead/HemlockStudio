/**
 * The EnumService class is a service that provides a method to convert an array of Enum values to an
 * array of their string representations.
 */
package com.HemlockStudiosWebsite.service;

import org.springframework.stereotype.Service;


@Service
public class EnumService {
    
   /**
    * The function takes an array of Enum objects and returns an array of their string representations.
    * 
    * @param enums An array of Enum objects.
    * @return The method is returning an array of strings, where each string represents the string
    * value of an enum element.
    */
    public String[] getEnumValuesAsString(Enum<?>[] enums) {
        String[] enumStrings = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            enumStrings[i] = enums[i].toString();
        }
        return enumStrings;
    }
}
