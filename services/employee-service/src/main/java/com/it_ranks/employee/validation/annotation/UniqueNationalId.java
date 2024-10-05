package com.it_ranks.employee.validation.annotation;

import com.it_ranks.employee.validation.validator.UniqueNationalIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNationalIdValidator.class)
public @interface UniqueNationalId {
    String message() default "{employee.nationalId.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
