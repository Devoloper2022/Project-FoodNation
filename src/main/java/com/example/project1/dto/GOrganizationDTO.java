package com.example.project1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GOrganizationDTO {
    private Long id;
    @NotEmpty
    private String name;

    private String description;
    private Long managerID;
}
