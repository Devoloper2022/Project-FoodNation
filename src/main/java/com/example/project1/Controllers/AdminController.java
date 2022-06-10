package com.example.project1.Controllers;


import com.example.project1.CustomTemplate.Payload.request.LoginRequest;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.User;
import com.example.project1.Facade.UserFacade;
import com.example.project1.Facade.dto.AdminDTO;
import com.example.project1.Facade.dto.UserDTO;
import com.example.project1.Security.JWTTokenProvider;
import com.example.project1.Services.TokenService;
import com.example.project1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserFacade userFacade;


    @PostMapping("/signin")
    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        return  ResponseEntity.ok(tokenService.creat(authentication));
    }


    @GetMapping("")
    public ResponseEntity<AdminDTO> getCurrentUser(Principal principal) {
        User user = userService.getCurrentUser(principal);
        AdminDTO admin = userFacade.userToAdminDTO(user);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


}
