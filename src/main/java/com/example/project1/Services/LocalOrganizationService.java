package com.example.project1.Services;


import com.example.project1.Domain.Dictionary.DOrganizationType;
import com.example.project1.Domain.Dictionary.DPosition;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;

import com.example.project1.Domain.User;
import com.example.project1.Repository.*;
import com.example.project1.dto.ItemDTO;
import com.example.project1.dto.LOrganizationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;


@Service
public class LocalOrganizationService {

    public static final Logger LOG = LoggerFactory.getLogger(LocalOrganizationService.class);

    private final GeneralOrganizationRepository generalOrganizationRepository;
    private final UserRepository userRepository;
    private final PositionRepository positionRepository;
    private final LocalOrganizationRepository localOrganizationRepository;
    private final RoleRepository roleRepository;
    private final OrganizationTypeRepository orgTypeRepository;


    @Autowired
    public LocalOrganizationService(GeneralOrganizationRepository generalOrganizationRepository, UserRepository userRepository, PositionRepository positionRepository, LocalOrganizationRepository localOrganizationRepository, RoleRepository roleRepository, OrganizationTypeRepository orgTypeRepository) {
        this.generalOrganizationRepository = generalOrganizationRepository;
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.localOrganizationRepository = localOrganizationRepository;
        this.roleRepository = roleRepository;
        this.orgTypeRepository = orgTypeRepository;
    }


    public LocalOrganization created(LOrganizationDTO localOrgDTO, Principal principal){

        User ceo=getUserByPrincipal(principal);
        Optional<GeneralOrganization> FindGenOrg=generalOrganizationRepository.findGeneralOrganizationByManager(ceo);
        GeneralOrganization genOrg=FindGenOrg.get();

        Optional<User> FindManager=userRepository.findUserById(localOrgDTO.getManagerID());
        User manager=FindManager.get();

        DPosition position=positionRepository.findByPosition("Manager").get();
        manager.getPositions().add(position);
        manager=userRepository.save(manager);


        LocalOrganization localOrganization=new LocalOrganization();
        localOrganization.setAddress(localOrgDTO.getAddress());
        localOrganization.setName(genOrg.getName());
        localOrganization.setManager(manager);
        localOrganization.setGeneralOrganization(genOrg);
        localOrganization.setCategory(convertLongToOrgType(localOrgDTO.getCategoryID()));
        localOrganization.setUrlImage(localOrgDTO.getUrlImage());
        localOrganization.setRate(0.0);
        localOrganization.setCounter(0);


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

        DPosition position =positionRepository.findByPosition("Manager").get();

        oldManager.getPositions().remove(position);
        userRepository.save(oldManager);

        Optional<User> FindManager=userRepository.findUserById(localOrgDTO.getManagerID());
        User manager=FindManager.get();


        manager.getPositions().add(position);
        userRepository.save(manager);

        localOrg.setName(localOrgDTO.getName());
        localOrg.setAddress(localOrgDTO.getAddress());
        localOrg.setManager(manager);
        localOrg.setCategory(convertLongToOrgType(localOrgDTO.getCategoryID()));

        LOG.info("Update Local Organization"+localOrg.getId() +"for organization" + localOrg.getGeneralOrganization().getId());
        return localOrganizationRepository.save(localOrg);
    }

    public LocalOrganization rateOrg(ItemDTO rate,Principal principal){
        User user=getUserByPrincipal(principal);
        LocalOrganization org=localOrganizationRepository.findLocalOrganizationById(rate.getOrgID()).get();

        Optional<String> checkUser=org.getRatedUser()
                .stream()
                .filter(u->u.equals(user.getUsername())).findAny();

        if (checkUser.isPresent()){
            return org;
        }else {
            Double sum =org.getRate() * org.getCounter() + rate.getRate();
            Integer users=  org.getCounter() + 1;
            Double result=sum/users;

            org.setRate(result);
            org.setCounter(users);
            org.getRatedUser().add(user.getUsername());
        }

        return localOrganizationRepository.save(org);
    }

    public List<LocalOrganization> listByGenID(Long genID){
        GeneralOrganization genOrg=generalOrganizationRepository.findGeneralOrganizationById(genID).get();
        return localOrganizationRepository.findByGeneralOrganization(genOrg);
    }

    public List<LocalOrganization> getAll(){
        return (List<LocalOrganization>) localOrganizationRepository.findAll();
    }

    private Set<DOrganizationType> convertLongToOrgType(Set<Long> list) {
        Set<DOrganizationType> types = new HashSet<>();

        Iterator<Long> i = list.iterator();
        while (i.hasNext()) {
            DOrganizationType orgType = orgTypeRepository.findById(i.next().longValue()).get();
            types.add(orgType);
        }

        return types;
    }

    private User getUserByPrincipal(Principal principal){
        String username =principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(()->new
                        UsernameNotFoundException("User not found with username: "+username));
    }
}
