package com.HemlockStudiosWebsite.service;

import org.springframework.stereotype.Service;


@Service
public class EnumService {
    
    public String[] getEnumValuesAsString(Enum<?>[] enums) {
        String[] enumStrings = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            enumStrings[i] = enums[i].toString();
        }
        return enumStrings;
    }
}
