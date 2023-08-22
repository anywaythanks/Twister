package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table(name = "case_slots")
//@Check(constraints = "sum(percentage_wining)=1 group by case_id")
public class CaseSlot<T extends Item> extends Slot<T> {
    @Range(min = 0, max = 1)
    @Column(name = "percentage_wining", nullable = false)
    BigDecimal percentageWining;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name", nullable = false)
    CaseSlotName name;

    protected CaseSlot() {
    }

    public CaseSlot(T item, Integer quantityItem, BigDecimal percentageWining) {
        super(item, quantityItem);
        this.percentageWining = percentageWining;
        this.name = new CaseSlotName();
    }

    public BigDecimal getPercentageWining() {
        return percentageWining;
    }

    public CaseSlotName getName() {
        return name;
    }
}
