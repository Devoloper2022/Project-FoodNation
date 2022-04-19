package com.example.project1.Services;

import com.example.project1.Domain.Dictionary.DRole;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;

import com.example.project1.Domain.User;
import com.example.project1.Repository.GeneralOrganizationRepository;
import com.example.project1.Repository.LocalOrganizationRepository;
import com.example.project1.Repository.RoleRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.dto.LOrganizationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;


@Service
public class LocalOrganizationService {

    public static final Logger LOG = LoggerFactory.getLogger(LocalOrganizationService.class);

    private final GeneralOrganizationRepository generalOrganizationRepository;
    private final UserRepository userRepository;
    private final LocalOrganizationRepository localOrganizationRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public LocalOrganizationService(GeneralOrganizationRepository generalOrganizationRepository, UserRepository userRepository, LocalOrganizationRepository localOrganizationRepository, RoleRepository roleRepository) {
        this.generalOrganizationRepository = generalOrganizationRepository;
        this.userRepository = userRepository;
        this.localOrganizationRepository = localOrganizationRepository;
        this.roleRepository = roleRepository;
    }

    public LocalOrganization created(LOrganizationDTO localOrgDTO, Principal principal){

        User ceo=getUserByPrincipal(principal);
        Optional<GeneralOrganization> FindGenOrg=generalOrganizationRepository.findGeneralOrganizationByManager(ceo);
        GeneralOrganization genOrg=FindGenOrg.get();

        Optional<User> FindManager=userRepository.findUserById(localOrgDTO.getManagerID());
        User manager=FindManager.get();

        DRole role =roleRepository.findByRole("Manager").get();

        manager.getRoles().add(role);
        userRepository.save(manager);

        LocalOrganization localOrganization=new LocalOrganization();
        localOrganization.setAddress(localOrgDTO.getAddress());
        localOrganization.setName(genOrg.getName());
        localOrganization.setManager(manager);
        localOrganization.setGeneralOrganization(genOrg);
        localOrganization.setRate(0);


        LOG.info("Saving Local Organization for organization" + genOrg.getId());
        return localOrganizationRepository.save(localOrganization);
    }

    public LocalOrganization getCurrentLocalOrg(Principal principal){
        Long id=getUserByPrincipal(principal).getLocalOrganization().getId();
        LocalOrganization organization=localOrganizationRepository.findLocalOrganizationById(id).get();
        return organization;
    }

    public LocalOrganization getLocalOrgByID(Long id){
        return localOrganizationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Can not be found with ID:"+id));
    }

    public LocalOrganization updateLocalOrg(LOrganizationDTO localOrgDTO){
        LocalOrganization localOrg=getLocalOrgByID(localOrgDTO.getId());
        User oldManager=localOrg.getManager();

        DRole role =roleRepository.findByRole("Manager").get();

        oldManager.getRoles().remove(role);
        userRepository.save(oldManager);

        Optional<User> FindManager=userRepository.findUserById(localOrgDTO.getManagerID());
        User manager=FindManager.get();


        manager.getRoles().add(role);
        userRepository.save(manager);

        localOrg.setName(localOrgDTO.getName());
        localOrg.setAddress(localOrgDTO.getAddress());
        localOrg.setManager(manager);

        LOG.info("Update Local Organization"+localOrg.getId() +"for organization" + localOrg.getGeneralOrganization().getId());
        return localOrganizationRepository.save(localOrg);
    }

    private User getUserByPrincipal(Principal principal){
        String username =principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(()->new
                        UsernameNotFoundException("User not found with username: "+username));
    }
}
