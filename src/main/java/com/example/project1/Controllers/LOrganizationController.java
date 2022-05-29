package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Facade.LOrganizationFacade;
import com.example.project1.Services.LocalOrganizationService;
import com.example.project1.dto.LOrganizationDTO;
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
@RequestMapping("/api/lo")
@CrossOrigin
public class LOrganizationController {
    @Autowired
    private LOrganizationFacade organizationFacade;
    @Autowired
    private LocalOrganizationService organizationService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping("/")
    public ResponseEntity<LOrganizationDTO> getLocalOrgProfile(Principal principal) {
        LocalOrganization organization = organizationService.getCurrentLocalOrg(principal);
        LOrganizationDTO organizationDTO = organizationFacade.LOtoLODTO(organization);
        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<LOrganizationDTO> getLocalOrgById(@PathVariable("orgId") String orgId) {
        LocalOrganization organization = organizationService.getLocalOrgByID(Long.parseLong(orgId));
        LOrganizationDTO orgDTO = organizationFacade.LOtoLODTO(organization);
        return new ResponseEntity<>(orgDTO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateLocalOrg(@Valid @RequestBody LOrganizationDTO organizationDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        LocalOrganization organization = organizationService.updateLocalOrg(organizationDTO);
        LOrganizationDTO organizationUpdated = organizationFacade.LOtoLODTO(organization);
        return new ResponseEntity<>(organizationUpdated, HttpStatus.OK);
    }

    @PostMapping("/org/create")
    public ResponseEntity<Object> registerGenOrg(@Valid @RequestBody LOrganizationDTO localDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        organizationService.created(localDTO, principal);
        return ResponseEntity.ok(new MessageResponse("Local Organization created successfully"));
    }

    @GetMapping("/all/{genID}")
    public ResponseEntity<List<LOrganizationDTO>> getListByGenId(@PathVariable("genID") String genID) {
        List<LOrganizationDTO> loDTOList=organizationService.listByGenID(Long.parseLong(genID))
                .stream()
                .map(organizationFacade::LOtoLODTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(loDTOList,HttpStatus.OK);
    }
}