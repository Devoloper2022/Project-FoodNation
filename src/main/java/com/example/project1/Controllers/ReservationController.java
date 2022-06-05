package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Facade.ReservationFacade;
import com.example.project1.Services.ReservationService;
import com.example.project1.dto.OrderDTO;
import com.example.project1.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/service/reservation")
public class ReservationController {
    @Autowired
    private ReservationService service;
    @Autowired
    private ReservationFacade facade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;



    @PostMapping("/create")
    public ResponseEntity<Object> reservation(@Valid @RequestBody ReservationDTO dto, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        service.create(dto, principal);
        return ResponseEntity.ok(new MessageResponse(" Successfully reserved"));
    }

}
