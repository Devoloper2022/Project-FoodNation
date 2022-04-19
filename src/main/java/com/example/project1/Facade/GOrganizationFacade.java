package com.example.project1.Facade;

import com.example.project1.Domain.GeneralOrganization;

import com.example.project1.dto.GOrganizationDTO;
import com.example.project1.dto.LOrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class GOrganizationFacade {
    public GOrganizationDTO GOtoGODTO(GeneralOrganization organization){
        GOrganizationDTO organizationDTO=new GOrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setDescription(organization.getDescription());
        organizationDTO.setManagerID(organization.getManager().getId());

        return organizationDTO;
    }
}
