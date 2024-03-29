package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.User;
import com.example.project1.Facade.UserFacade;
import com.example.project1.Facade.dto.SUserDTO;
import com.example.project1.Services.StaffService;
import com.example.project1.Facade.dto.StaffDTO;
import com.example.project1.Facade.dto.UserDTO;
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
@RequestMapping("/api/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping("/")
    public ResponseEntity<SUserDTO> getCurrentStaff(Principal principal){
        User user =staffService.getCurrentUser(principal);
        SUserDTO userDTO=null;
        if (user.getPositions().isEmpty()){
            userDTO=userFacade.userToAdminDTO(user);
        }else {
             userDTO=userFacade.userToStaffDTO(user);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<StaffDTO> getStaffProfile(@PathVariable("userId") String userId){
        User user= staffService.getStaffById(Long.parseLong(userId));
        StaffDTO staffDTO = userFacade.userToStaffDTO(user);
        return new ResponseEntity<>(staffDTO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public  ResponseEntity<Object> updateStaff(@Valid @RequestBody StaffDTO staffDTO, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        User user= staffService.updateStaff(staffDTO,principal);
        UserDTO userUpdated = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }


    @GetMapping("/all/go")
    public ResponseEntity<List<StaffDTO>> getListGenOrg(Principal principal) {
        List<StaffDTO> staffDTOList=staffService.getListGO(principal)
                .stream()
                .map(userFacade::userToStaffDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(staffDTOList,HttpStatus.OK);
    }

    @GetMapping("/all/lo")
    public ResponseEntity<List<StaffDTO>> getListLocOrg(Principal principal) {
        List<StaffDTO> staffDTOList=staffService.getListLO(principal)
                .stream()
                .map(userFacade::userToStaffDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(staffDTOList,HttpStatus.OK);
    }

    @GetMapping("/all/{localId}")
    public ResponseEntity<List<StaffDTO>> getListLocOrg(@PathVariable("localId") String id,Principal principal) {
        List<StaffDTO> staffDTOList=staffService.getListLOById(Long.parseLong(id))
                .stream()
                .map(userFacade::userToStaffDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(staffDTOList,HttpStatus.OK);
    }


}
