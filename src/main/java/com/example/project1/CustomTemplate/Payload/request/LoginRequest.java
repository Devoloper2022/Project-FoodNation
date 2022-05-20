package com.example.project1.CustomTemplate.Payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "Username can not be empty")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    private String password;
}
