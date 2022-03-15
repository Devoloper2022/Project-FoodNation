package com.example.project1.CustomTemp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StaffExistException extends RuntimeException {
    public StaffExistException(@NotEmpty(message = "Please enter your username") String s) {
    super(s);
    }
}
