package com.github.anywaythanks.twisterresource.validators;

import com.github.anywaythanks.twisterresource.annotation.SumEq;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.Percentage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.List;

public class SumPercentageEqValidator implements ConstraintValidator<SumEq, List<? extends Percentage>> {
    private BigDecimal eq;

    public void initialize(SumEq constraintAnnotation) {
        this.eq = new BigDecimal(constraintAnnotation.eqSum());
    }

    @Override
    public boolean isValid(List<? extends Percentage> value, ConstraintValidatorContext context) {
        return value.stream().map(Percentage::getPercentage).reduce(BigDecimal::add).orElseThrow().compareTo(eq) == 0;
    }
}
