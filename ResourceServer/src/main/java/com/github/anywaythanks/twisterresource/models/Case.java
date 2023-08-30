package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;

@Entity
@Table(name = "cases")
@NamedEntityGraph(name = "Case.detail",
        attributeNodes = @NamedAttributeNode("price"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
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
    @Column(name = "visible_name", nullable = false)
    @NonNull
    @Setter
    String visibleName;
    @NotNull
    @Length(max = 1000)
    @Column(nullable = false)
    @NonNull
    @Setter
    String description;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    })
    @NonNull
    @Setter
    Money price;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "case_id", nullable = false)
    @Cascade(ALL)
    @BatchSize(size = 25)
    @OrderBy("percentageWining desc")
    @NonNull
    Set<CaseSlot<Item>> caseSlotSet = new HashSet<>();
    @NotNull
    @NonNull
    @Setter
    Duration cooldown;
    @NotNull
    @Column(name = "modified_by", nullable = false)
    @NonNull
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    @NonNull
    Instant createdOn;
}