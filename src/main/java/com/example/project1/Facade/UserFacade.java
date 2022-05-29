package com.example.project1.Facade;

import com.example.project1.Domain.Dictionary.DPosition;
import com.example.project1.Domain.User;
import com.example.project1.dto.StaffDTO;
import com.example.project1.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class UserFacade {
    public UserDTO userToUserDTO(User user){
        UserDTO userDTO= new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstName());
        userDTO.setLastname(user.getSecondName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public StaffDTO userToStaffDTO(User user){
        StaffDTO userDTO= new StaffDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstName());
        userDTO.setLastname(user.getSecondName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setOrganizationId(user.getLocalOrganization().getId());
        userDTO.setPositionID(convertPositionToLong(user.getPositions()));
        userDTO.setUrlImage(user.getUrlImage());

        return userDTO;
    }

    private Set<Long> convertPositionToLong(Set<DPosition> list) {
        Set<Long> positions = new HashSet<>();

        Iterator<DPosition> i = list.iterator();
        while (i.hasNext()) {
            positions.add(i.next().getId());
        }
        return positions;
    }
}
