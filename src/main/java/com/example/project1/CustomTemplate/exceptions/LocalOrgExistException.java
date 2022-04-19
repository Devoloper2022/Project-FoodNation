package com.example.project1.CustomTemplate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocalOrgExistException extends RuntimeException{
    public LocalOrgExistException(@NotEmpty (message = "") String s) {
        super(s);
    }
}
