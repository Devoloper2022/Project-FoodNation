package com.example.project1.Facade;


import com.example.project1.Domain.Dictionary.DOrganizationType;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.dto.LOrganizationDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class LOrganizationFacade {
    public LOrganizationDTO LOtoLODTO(LocalOrganization organization){
        LOrganizationDTO lOrganizationDTO=new LOrganizationDTO();
        lOrganizationDTO.setId(organization.getId());
        lOrganizationDTO.setName(organization.getName());
        lOrganizationDTO.setAddress(organization.getAddress());
        lOrganizationDTO.setManagerID(organization.getManager().getId());
        lOrganizationDTO.setGeneralOrganizationID(organization.getGeneralOrganization().getId());
        lOrganizationDTO.setCategoryID(convertOrgTypeToLong(organization.getCategory()));
        lOrganizationDTO.setUrlImage(organization.getUrlImage());
        lOrganizationDTO.setRate(organization.getRate());
        lOrganizationDTO.setUsers(organization.getCounter());

        return lOrganizationDTO;
    }

    private Set<Long> convertOrgTypeToLong(Set<DOrganizationType> list) {
        Set<Long> types = new HashSet<>();

        Iterator<DOrganizationType> i = list.iterator();
        while (i.hasNext()) {
            types.add(i.next().getId());
        }
        return types;
    }
}
