package com.example.project1.Facade;

import com.example.project1.Domain.GeneralOrganization;

import com.example.project1.Facade.dto.GOrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class GOrganizationFacade {
    public GOrganizationDTO GOtoGODTO(GeneralOrganization organization){
        GOrganizationDTO organizationDTO=new GOrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setDescription(organization.getDescription());
        organizationDTO.setManagerID(organization.getManager().getId());
        organizationDTO.setFoodsType(organization.getFoodType());
        organizationDTO.setUrlImage(organization.getUrlImage());
        organizationDTO.setCategory(organization.getCategory());

        return organizationDTO;
    }
}
