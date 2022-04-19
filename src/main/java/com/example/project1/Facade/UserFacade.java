package com.example.project1.Facade;

import com.example.project1.Domain.User;
import com.example.project1.dto.StaffDTO;
import com.example.project1.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {
    public UserDTO userToUserDTO(User user){
        UserDTO userDTO= new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstName());
        userDTO.setLastname(user.getSecondName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(userDTO.getPhoneNumber());
        return userDTO;
    }

    public StaffDTO userToStaffDTO(User user){
        StaffDTO userDTO= new StaffDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstName());
        userDTO.setLastname(user.getSecondName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(userDTO.getPhoneNumber());
        userDTO.setOrganizationId(user.getLocalOrganization().getId());
        userDTO.setRole(user.getRoles());
        return userDTO;
    }

}
