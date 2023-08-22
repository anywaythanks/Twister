package com.github.anywaythanks.twisterresource.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class SumEqValidator implements ConstraintValidator<SumEq, List<?>> {
    private Function<Object, BigDecimal> getter;
    private BigDecimal eq;

    public void initialize(SumEq constraintAnnotation) {
        try {
            var dc = constraintAnnotation.converter().getDeclaredConstructor();
            dc.setAccessible(true);
            this.getter = dc.newInstance();
            this.eq = new BigDecimal(constraintAnnotation.eqSum());
        } catch (InstantiationException | InvocationTargetException
                 | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return value.stream().map(getter).reduce(BigDecimal::add).orElseThrow().compareTo(eq) == 0;
    }
}
