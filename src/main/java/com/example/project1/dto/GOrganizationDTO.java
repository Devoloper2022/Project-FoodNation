package com.example.project1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class GOrganizationDTO {
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private String urlImage;
    private Long managerID;
}
