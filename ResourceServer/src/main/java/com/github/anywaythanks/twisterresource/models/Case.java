package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;
import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@Table(name = "cases")
@NamedEntityGraph(name = "Case.detail",
        attributeNodes = @NamedAttributeNode("price"))
public class Case {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    String name;
    @NotBlank
    @Length(min = 1, max = 64)
    @Column(nullable = false)
    String visibleName;
    @NotNull
    @Length(max = 1000)
    @Column(nullable = false)
    String description;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    })
    Money price;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "case_id", nullable = false)
    @Cascade(ALL)
    @BatchSize(size = 25)
    @OrderBy("percentageWining desc")
    Set<CaseSlot<Item>> caseSlotSet = new LinkedHashSet<>();
    @NotNull
    Duration cooldown;

    protected Case() {
    }

    public Case(String name, String visibleName, String description,
                Money price, Set<CaseSlot<Item>> caseSlotSet, Duration cooldown) {
        this.name = name;
        this.visibleName = visibleName;
        this.description = description;
        this.price = price;
        this.caseSlotSet = caseSlotSet;
        this.cooldown = cooldown;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCooldown(Duration cooldown) {
        this.cooldown = cooldown;
    }

    public Duration getCooldown() {
        return cooldown;
    }

    public Money getPrice() {
        return price;
    }

    public Set<CaseSlot<Item>> getCaseSlotSet() {
        return caseSlotSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setCaseSlotSet(Set<CaseSlot<Item>> caseSlotSet) {
        this.caseSlotSet = caseSlotSet;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public void setVisibleName(String visibleName) {
        this.visibleName = visibleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}