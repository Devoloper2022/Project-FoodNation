package com.example.project1.Facade;


import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Facade.dto.LOrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class LOrganizationFacade {
    public LOrganizationDTO LOtoLODTO(LocalOrganization organization){
        LOrganizationDTO lOrganizationDTO=new LOrganizationDTO();
        lOrganizationDTO.setId(organization.getId());
        lOrganizationDTO.setName(organization.getName());
        lOrganizationDTO.setAddress(organization.getAddress());
        lOrganizationDTO.setManagerID(organization.getManager().getId());
        lOrganizationDTO.setDescription(organization.getGeneralOrganization().getDescription());
        lOrganizationDTO.setGeneralOrganizationID(organization.getGeneralOrganization().getId());
        lOrganizationDTO.setCategoryID(organization.getCategory().getId());
        lOrganizationDTO.setUrlImage(organization.getUrlImage());
        lOrganizationDTO.setRate(organization.getRate());
        lOrganizationDTO.setUsers(organization.getCounter());
        return lOrganizationDTO;
    }
}
