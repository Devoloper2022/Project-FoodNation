package com.example.project1.CustomTemplate.annotations;

import com.example.project1.CustomTemplate.Validations.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message()default "Password do not match";

    Class<?>[]groups()default {};

    Class<? extends Payload>[] payload() default {};
}
