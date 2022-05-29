package com.example.project1.dto;


import com.example.project1.CustomTemplate.annotations.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class UserDTO {
     private Long id;
     @NotEmpty
     private String firstname;
     @NotEmpty
     private String lastname;

     private String urlImage;

     @NotEmpty
     private String username;

     @Email(message = "It should have email format")
     private String email;

     @NotEmpty(message = "Password is required")
     @Size(min = 6, max = 10)
     private String password;
     private String confirmPassword;

     private String phoneNumber;
}
