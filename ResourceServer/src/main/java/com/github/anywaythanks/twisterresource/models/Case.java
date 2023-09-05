package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.time.Instant;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "cases")
@NamedEntityGraph(name = "Case.detail",
        attributeNodes = @NamedAttributeNode("price"))
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
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
    @Column(name = "visible_name", nullable = false)
    @Setter
    String visibleName;
    @NotNull
    @Length(max = 1000)
    @Column(nullable = false)
    @Setter
    String description;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    })
    @Setter
    Money price;
    @NotNull
    @Setter
    Duration cooldown;
    @NotNull
    @Column(name = "modified_by", nullable = false)
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
}