package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Facade.GOrganizationFacade;
import com.example.project1.Services.GeneralOrganizationService;
import com.example.project1.Facade.dto.GOrganizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/go")
@CrossOrigin
public class GOrganizationController {

    @Autowired
    private GOrganizationFacade organizationFacade;
    @Autowired
    private GeneralOrganizationService organizationService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;


    @GetMapping("/")
    public ResponseEntity<GOrganizationDTO> getCurrentGenOrg(Principal principal) {
        GeneralOrganization organization = organizationService.getCurrentGenOrg(principal);
        GOrganizationDTO organizationDTO = organizationFacade.GOtoGODTO(organization);
        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<GOrganizationDTO> getGenOrgProfile(@PathVariable("orgId") String orgId) {
        GeneralOrganization organization = organizationService.getGeneralGenOrgByID(Long.parseLong(orgId));
        GOrganizationDTO orgDTO = organizationFacade.GOtoGODTO(organization);
        return new ResponseEntity<>(orgDTO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateGenOrg(@Valid @RequestBody GOrganizationDTO organizationDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        GeneralOrganization organization = organizationService.updateGenOrg(organizationDTO, principal);
        GOrganizationDTO organizationUpdated = organizationFacade.GOtoGODTO(organization);
        return new ResponseEntity<>(organizationUpdated, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GOrganizationDTO>> getListGenOrg(Principal principal) {
       List<GOrganizationDTO> goDTOList=organizationService.getListGenOrg()
               .stream()
               .map(organizationFacade::GOtoGODTO)
               .collect(Collectors.toList());

       return new ResponseEntity<>(goDTOList,HttpStatus.OK);
    }

}
