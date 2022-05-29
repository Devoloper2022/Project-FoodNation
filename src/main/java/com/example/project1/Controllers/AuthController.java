package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.Token;
import com.example.project1.Security.JWTTokenProvider;
import com.example.project1.CustomTemplate.Payload.request.LoginRequest;
import com.example.project1.CustomTemplate.Payload.request.SignUpRequest;
import com.example.project1.CustomTemplate.Payload.response.JWTTokenSuccessResponse;
import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.Security.SecurityConstants;
import com.example.project1.Services.TokenService;
import com.example.project1.Services.UserService;
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

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

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

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        userService.creatUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

}
