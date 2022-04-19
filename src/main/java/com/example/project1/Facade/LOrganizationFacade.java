package com.example.project1.Facade;

import com.example.project1.Domain.LocalOrganization;
import com.example.project1.dto.LOrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class LOrganizationFacade {
    public LOrganizationDTO LOtoLODTO(LocalOrganization organization){
        LOrganizationDTO lOrganizationDTO=new LOrganizationDTO();
        lOrganizationDTO.setId(organization.getId());
        lOrganizationDTO.setName(organization.getName());
        lOrganizationDTO.setAddress(organization.getAddress());
        lOrganizationDTO.setManagerID(organization.getManager().getId());
        lOrganizationDTO.setGeneralOrganizationID(organization.getGeneralOrganization().getId());

        return lOrganizationDTO;
    }
}
