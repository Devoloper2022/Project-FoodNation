package com.example.project1.Facade.dto;

import com.example.project1.CustomTemplate.annotations.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class AdminDTO implements SUserDTO{
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

    private String name;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 10)
    private String password;
    private String confirmPassword;
}
