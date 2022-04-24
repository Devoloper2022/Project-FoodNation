package com.example.project1.CustomTemplate.Validations;

import com.example.project1.CustomTemplate.Payload.request.RequestSignUp;
import com.example.project1.CustomTemplate.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RequestSignUp userSignUp = (RequestSignUp) o;
        return userSignUp.getPassword().equals(userSignUp.getConfirmPassword());
    }
}
