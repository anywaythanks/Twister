package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table(name = "case_slots")
//@Check(constraints = "sum(win_rate)=1 group by case_id")
@NamedEntityGraph(name = "CaseSlot.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
public class CaseSlot<T extends Item> extends Slot<T> {
    public static final long MIN_PERCENTAGE = 0, MAX_PERCENTAGE = 100;
    @Range(min = MIN_PERCENTAGE, max = MAX_PERCENTAGE)
    @Column(name = "win_rate", nullable = false)
    BigDecimal winRate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name", nullable = false)
    CaseSlotName name;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    Case ownerCase;
}
