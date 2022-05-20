package com.example.project1.Services;

import com.example.project1.CustomTemplate.Payload.request.StaffAddRequest;
import com.example.project1.CustomTemplate.exceptions.UserExistException;
import com.example.project1.Domain.Dictionary.DRole;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Domain.User;
import com.example.project1.Repository.GeneralOrganizationRepository;
import com.example.project1.Repository.LocalOrganizationRepository;
import com.example.project1.Repository.RoleRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.dto.StaffDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class StaffService {
    public static final Logger LOG = LoggerFactory.getLogger(StaffService.class);

    private final UserRepository userRepository;
    private final LocalOrganizationRepository localOrganizationRepository;
    private final GeneralOrganizationRepository generalOrganizationRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public StaffService(UserRepository userRepository, LocalOrganizationRepository localOrganizationRepository, GeneralOrganizationRepository generalOrganizationRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.localOrganizationRepository = localOrganizationRepository;
        this.generalOrganizationRepository = generalOrganizationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User creatStaff(StaffAddRequest userIn) {
        User user = new User();
        DRole role =roleRepository.findById(userIn.getRoleId()).get();
        LocalOrganization office= localOrganizationRepository.findById(userIn.getOrganizationID()).get();

        user.setFirstName(userIn.getFirstName());
        user.setSecondName(userIn.getSecondName());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setEmail(userIn.getEmail());
        user.setPhoneNumber(userIn.getPhoneNumber());
        user.getRoles().add(role);
        user.setLocalOrganization(office);

        try {
            LOG.info("Save staff {} ", userIn.getEmail() + " " + userIn.getOrganizationID());
            return userRepository.save(user);
        } catch (Exception ex) {
            LOG.error("Error during creation of staff", ex.getMessage());
            throw new UserExistException("The staff " + userIn.getUsername() + " already exist.Please check your credentials");
        }
    }

    public User updateStaff(StaffDTO staffDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setFirstName(staffDTO.getFirstname());
        user.setSecondName(staffDTO.getLastname());
        user.setPhoneNumber(staffDTO.getPhoneNumber());

        LOG.info("Update user {} ", staffDTO.getEmail());
        return userRepository.save(user);
    }

    public User updateRole(StaffDTO staffDTO) {
        User user = userRepository.findUserById(staffDTO.getId()).get();
        user.setUsername(staffDTO.getUsername());
        user.setEmail(staffDTO.getEmail());
        user.getRoles().addAll(staffDTO.getRole());

        LOG.info("Update role user {} of ", staffDTO.getEmail());
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

        return  userRepository.findAllByGeneralOrganization(org.getId());
    }

    public List<User> getListLO(Principal principal){
        User user = getUserByPrincipal(principal);
        LocalOrganization org=user.getLocalOrganization();

        return  userRepository.findAllByGeneralOrganization(org.getId());
    }

    public List<User> getListLOById(Long id){
        return  userRepository.findAllByGeneralOrganization(id);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }
}
