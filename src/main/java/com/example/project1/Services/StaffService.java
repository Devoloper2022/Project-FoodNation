package com.example.project1.Services;

import com.example.project1.CustomTemplate.Payload.request.StaffAddRequest;
import com.example.project1.CustomTemplate.exceptions.UserExistException;
import com.example.project1.Domain.Dictionary.DPosition;
import com.example.project1.Domain.Dictionary.DRole;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Domain.User;
import com.example.project1.Repository.*;
import com.example.project1.Facade.dto.StaffDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class StaffService {
    public static final Logger LOG = LoggerFactory.getLogger(StaffService.class);

    private final UserRepository userRepository;
    private final LocalOrganizationRepository localOrganizationRepository;
    private final PositionRepository posRepository;
    private final GeneralOrganizationRepository generalOrganizationRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public StaffService(UserRepository userRepository, LocalOrganizationRepository localOrganizationRepository, PositionRepository posRepository, GeneralOrganizationRepository generalOrganizationRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.localOrganizationRepository = localOrganizationRepository;
        this.posRepository = posRepository;
        this.generalOrganizationRepository = generalOrganizationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User creatStaff(StaffAddRequest userIn , Principal principal) {
        User user = new User();

        DRole role =roleRepository.findByRole("Staff").get();

        User chief=getUserByPrincipal(principal);
        LocalOrganization office= localOrganizationRepository.findById(chief.getLocalOrganization().getId()).get();

        user.setFirstName(userIn.getFirstName());
        user.setSecondName(userIn.getSecondName());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setEmail(userIn.getEmail());
        user.setPhoneNumber(userIn.getPhoneNumber());
        user.getRoles().add(role);
        user.setPositions(convertLongToPosition(userIn.getPositionId()));
        user.setUrlImage(userIn.getUrlImage());
        user.setLocalOrganization(office);
        user.setGeneralOrganization(office.getGeneralOrganization());

        try {
            LOG.info("Save staff {} ", userIn.getEmail() + " " + office.getId());
            return userRepository.save(user);
        } catch (Exception ex) {
            LOG.error("Error during creation of staff", ex.getMessage());
            throw new UserExistException("The staff " + userIn.getUsername() + " already exist.Please check your credentials");
        }
    }

    public User updateStaff(StaffDTO staffDTO, Principal principal) {
        User user = getUserByPrincipal(principal);

        user.setPassword(passwordEncoder.encode(staffDTO.getPassword()));
        user.setFirstName(staffDTO.getFirstname());
        user.setSecondName(staffDTO.getLastname());
        user.setPhoneNumber(staffDTO.getPhoneNumber());
        user.setEmail(staffDTO.getEmail());
        user.setPositions(convertLongToPosition(staffDTO.getPositionID()));
        user.setUrlImage(staffDTO.getUrlImage());

        LOG.info("Update user {} ", staffDTO.getEmail());
        return userRepository.save(user);
    }

    public User getStaffById(Long id) {
        return userRepository.findUserById(id).get();
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }



    public List<User> getListGO(Principal principal){
        User user = getUserByPrincipal(principal);
        GeneralOrganization org=user.getLocalOrganization().getGeneralOrganization();

        return  userRepository.findAllByGeneralOrganization(org);
    }

    public List<User> getListLO(Principal principal){
        User user = getUserByPrincipal(principal);
        LocalOrganization org=user.getLocalOrganization();

        return  userRepository.findAllByLocalOrganization(org);
    }

    public List<User> getListLOById(Long id){
        LocalOrganization organization = localOrganizationRepository.findById(id).get();
        return  userRepository.findAllByLocalOrganization(organization);
    }


    private Set<DPosition> convertLongToPosition(Set<Long> list) {
        Set<DPosition> positions = new HashSet<>();

        Iterator<Long> i = list.iterator();
        while (i.hasNext()) {
            DPosition position = posRepository.findById(i.next().longValue()).get();
            positions.add(position);
        }

        return positions;
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }
}
