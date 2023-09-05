package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table(name = "case_slots")
//@Check(constraints = "sum(percentage_wining)=1 group by case_id")
@NamedEntityGraph(name = "CaseSlot.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
public class CaseSlot<T extends Item> extends Slot<T> {
    @Range(min = 0, max = 1)
    @Column(name = "percentage_wining", nullable = false)
    BigDecimal percentageWining;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name", nullable = false)
    CaseSlotName name;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    Case ownerCase;
}
