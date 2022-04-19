package com.example.project1.Services;

import com.example.project1.CustomTemplate.Payload.request.GeneralOrganizationSignUpRequest;
import com.example.project1.CustomTemplate.exceptions.GeneralOrganizationExistException;
import com.example.project1.CustomTemplate.exceptions.GeneralOrganizationNotFoundException;
import com.example.project1.Domain.Dictionary.DRole;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Domain.User;
import com.example.project1.Repository.GeneralOrganizationRepository;
import com.example.project1.Repository.LocalOrganizationRepository;
import com.example.project1.Repository.RoleRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.dto.GOrganizationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class GeneralOrganizationService {
    public static final Logger LOG = LoggerFactory.getLogger(GeneralOrganizationService.class);

    private final GeneralOrganizationRepository generalOrganizationRepository;
    private final UserRepository userRepository;
    private final LocalOrganizationRepository localOrganizationRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public GeneralOrganizationService(GeneralOrganizationRepository generalOrganizationRepository, UserRepository userRepository, LocalOrganizationRepository localOrganizationRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.generalOrganizationRepository = generalOrganizationRepository;
        this.userRepository = userRepository;
        this.localOrganizationRepository = localOrganizationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public GeneralOrganization created(GeneralOrganizationSignUpRequest genOrg) {

        GeneralOrganization generalOrganization = new GeneralOrganization();
        LocalOrganization localOrganization = new LocalOrganization();
        User user = new User();
        DRole ceo = roleRepository.findByRole("CEO").get();
        DRole manager = roleRepository.findByRole("Manager").get();

        //Creation user
        user.setEmail(genOrg.getEmail());
        user.setUsername(genOrg.getUsername());
        user.setFirstName(genOrg.getFirstName());
        user.setSecondName(genOrg.getSecondName());
        user.setPassword(passwordEncoder.encode(genOrg.getPassword()));
        user.setPhoneNumber(genOrg.getPhoneNumber());
        user.getRoles().add(ceo);
        user.getRoles().add(manager);
        //Saving user into DB and getting with ID
        User founder=userRepository.save(user);


        //Creating gen org
        generalOrganization.setName(genOrg.getName());
        generalOrganization.setDescription(genOrg.getDescription());
        generalOrganization.setManager(founder);
        //Saving gen org into DB and getting with ID
        GeneralOrganization organization=generalOrganizationRepository.save(generalOrganization);


        //Creation local org
        localOrganization.setName(genOrg.getName());
        localOrganization.setRate(0);
        localOrganization.setAddress(genOrg.getAddress());
        //Saving local org into DB and get with ID
        LocalOrganization office=localOrganizationRepository.save(localOrganization);



//        user.setLocalOrganization(office);

        //Updating local org due to creation user
        office.setManager(founder);
//        office.getUserList().add(founder);
        office.setGeneralOrganization(organization);
        //Updating local org due to creation gen org
        office=localOrganizationRepository.save(office);

        founder.setLocalOrganization(office);
        founder=userRepository.save(founder);



        try {
            LOG.info("Save general Organization with name" + genOrg.getName(), user.getUsername());
            return generalOrganizationRepository.save(generalOrganization);
        } catch (Exception ex) {
            LOG.error("Error during creation ", ex.getMessage());
            throw new GeneralOrganizationExistException("The general organization " + generalOrganization.getName() + " already exist.Please check your credentials");
        }
    }

    public void delete(Principal principal) {
        GeneralOrganization generalOrganization = getCurrentGenOrg(principal);
        generalOrganizationRepository.delete(generalOrganization);
    }

    public GeneralOrganization updateGenOrg(GOrganizationDTO organizationDTO, Principal principal) {
        GeneralOrganization organization = getCurrentGenOrg(principal);

        organization.setName(organizationDTO.getName());
        organization.setDescription(organizationDTO.getDescription());

        return organization;
    }

    public GeneralOrganization getCurrentGenOrg(Principal principal) {
        User user = getUserByPrincipal(principal);
        return generalOrganizationRepository.findById(user.getLocalOrganization().getGeneralOrganization().getId())
                .orElseThrow(() ->
                        new GeneralOrganizationNotFoundException
                                ("General Organization cannot be found for user: " + user.getUsername()));
    }

    public GeneralOrganization getGeneralGenOrgByID(Long id) {
        return generalOrganizationRepository.findById(id).get();
    }

    public List<GeneralOrganization> getListGenOrg() {
        return (List<GeneralOrganization>) generalOrganizationRepository.findAll();
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }
}
