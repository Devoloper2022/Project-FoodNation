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
public class UserRequest implements RequestSignUp{
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;


    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 10)
    private String password;
    private String confirmPassword;

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    private String phoneNumber;
}
