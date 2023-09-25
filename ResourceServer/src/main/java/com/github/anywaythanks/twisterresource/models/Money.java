package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
@EqualsAndHashCode
public class Money {
    @NotNull
    BigDecimal value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "money_type_id", nullable = false)
    MoneyType moneyType;
}
