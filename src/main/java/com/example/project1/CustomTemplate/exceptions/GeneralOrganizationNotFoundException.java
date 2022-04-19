package com.example.project1.CustomTemplate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GeneralOrganizationNotFoundException extends RuntimeException{
    public GeneralOrganizationNotFoundException(String s) {
        super(s);
    }
}
