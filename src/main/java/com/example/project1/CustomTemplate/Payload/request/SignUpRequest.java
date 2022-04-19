package com.example.project1.CustomTemplate.Payload.request;

import com.example.project1.CustomTemplate.annotations.PasswordMatches;
import com.example.project1.CustomTemplate.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@PasswordMatches
public class SignUpRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;

    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Please enter your firstname")
    private String firstName;
    @NotEmpty(message = "Please enter your lastname")
    private String secondName;


    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 10)
    private String password;
    private String confirmPassword;


    @NotEmpty(message = "Please enter your phone number")
    @Size(min = 9, max = 11)
    private String phoneNumber;
}
