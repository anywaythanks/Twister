package com.github.anywaythanks.twisterresource.annotation;

import com.github.anywaythanks.twisterresource.validators.SumPercentageEqValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SumPercentageEqValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SumEq {
    String message() default "Invalid sum.";

    String eqSum();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
