package com.example.project1.dto;

import com.example.project1.CustomTemplate.annotations.PasswordMatches;
import com.example.project1.CustomTemplate.annotations.ValidEmail;
import com.example.project1.Domain.Dictionary.DRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@PasswordMatches
public class StaffDTO {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;

    private String urlImage;

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 10)
    private String password;
    private String confirmPassword;

    private String phoneNumber;
    private Long OrganizationId;
    private Set<Long> positionID = new HashSet<>();
}
