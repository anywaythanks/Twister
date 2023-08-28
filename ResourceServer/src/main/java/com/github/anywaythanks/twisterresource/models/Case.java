package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;

@Entity
@Table(name = "cases")
@NamedEntityGraph(name = "Case.detail",
        attributeNodes = @NamedAttributeNode("price"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Setter
@Getter
public class Case {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    @NonNull
    String name;
    @NotBlank
    @Length(min = 1, max = 64)
    @Column(nullable = false)
    @NonNull
    String visibleName;
    @NotNull
    @Length(max = 1000)
    @Column(nullable = false)
    @NonNull
    String description;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    })
    @NonNull
    Money price;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "case_id", nullable = false)
    @Cascade(ALL)
    @BatchSize(size = 25)
    @OrderBy("percentageWining desc")
    @NonNull
    Set<CaseSlot<Item>> caseSlotSet;
    @NotNull
    @NonNull
    Duration cooldown;
}