package com.example.project1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LOrganizationDTO {
    private Long id;
    private String name;
    @NotEmpty
    private String address;
    private Long managerID;
    private Long generalOrganizationID;

}
