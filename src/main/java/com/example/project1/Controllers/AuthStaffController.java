package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Payload.request.GeneralOrganizationSignUpRequest;
import com.example.project1.CustomTemplate.Payload.request.LoginRequest;
import com.example.project1.CustomTemplate.Payload.request.StaffAddRequest;
import com.example.project1.CustomTemplate.Payload.response.JWTTokenSuccessResponse;
import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Security.JWTTokenProvider;
import com.example.project1.Security.SecurityConstants;
import com.example.project1.Services.GeneralOrganizationService;
import com.example.project1.Services.StaffService;
import com.example.project1.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthStaffController {
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private StaffService staffService;
    @Autowired
    private GeneralOrganizationService generalOrganizationService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/staff/signin")
    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        return  ResponseEntity.ok(tokenService.creat(authentication));
    }

    @PostMapping("/org/signup")
    public ResponseEntity<Object> registerGenOrg(@Valid @RequestBody GeneralOrganizationSignUpRequest signUpRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        generalOrganizationService.created(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("General Organization registered successfully"));
    }

    @PostMapping("/staff/add")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody StaffAddRequest signUpRequest, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        staffService.creatStaff(signUpRequest,principal);
        return ResponseEntity.ok(new MessageResponse("Staff added successfully"));
    }
}
