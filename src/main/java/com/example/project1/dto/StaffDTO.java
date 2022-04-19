package com.example.project1.dto;

import com.example.project1.CustomTemplate.annotations.ValidEmail;
import com.example.project1.Domain.Dictionary.DRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
public class StaffDTO {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    private String phoneNumber;
    private Long OrganizationId;
    private Set<DRole> role = new HashSet<>();
}
