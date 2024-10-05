package com.it_ranks.employee.validation.annotation;

import com.it_ranks.employee.validation.validator.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAgeFromNationalId {
    String message() default "{employee.age.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
