package com.example.project1.CustomTemp.Validations;

import com.example.project1.CustomTemp.annotations.PasswordMatches;
import com.example.project1.Security.Payload.request.SignUpRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignUpRequest userSignUpRequest=(SignUpRequest) o;
        return userSignUpRequest.getPassword().equals(userSignUpRequest.getConfirmPassword());
    }
}
