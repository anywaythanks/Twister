package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table(name = "case_slots")
//@Check(constraints = "sum(percentage_wining)=1 group by case_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CaseSlot<T extends Item> extends Slot<T> {
    @Range(min = 0, max = 1)
    @Column(name = "percentage_wining", nullable = false)
    @NonNull
    BigDecimal percentageWining;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name", nullable = false)
    @NonNull
    CaseSlotName name;

    public CaseSlot(@NonNull T item, @NonNull Integer quantityItem, @NonNull BigDecimal percentageWining) {
        super(item, quantityItem);
        this.percentageWining = percentageWining;
        this.name = new CaseSlotName();
    }
}
