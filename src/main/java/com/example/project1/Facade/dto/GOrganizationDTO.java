package com.example.project1.Facade.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
public class GOrganizationDTO {
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private String urlImage;
    private Long managerID;
    private Set<String> category;
    private Set<String> foodsType;
}
