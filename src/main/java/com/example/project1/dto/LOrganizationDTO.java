package com.example.project1.dto;

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
    private Long generalOrganizationID;
    private Set<Long> categoryID;

}
