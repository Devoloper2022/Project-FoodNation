package com.example.project1.CustomTemplate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionText extends RuntimeException {
    public ExceptionText(@NotEmpty(message = "") String s) {
        super(s);
    }
}
