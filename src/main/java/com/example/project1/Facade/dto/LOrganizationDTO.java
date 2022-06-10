package com.example.project1.Facade.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class LOrganizationDTO {
    private Long id;
    private String name;
    @NotEmpty
    private String address;
    private Long managerID;
    private String urlImage;
    private Double Rate;
    private Integer users;
    private String description;
    private Long generalOrganizationID;
    private Long categoryID;

}
