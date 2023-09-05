package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
public abstract class Item {
    @Id
    @GeneratedValue(generator = "ITEM_ID_GENERATOR")
    @Setter
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

//    String picturePath; //TODO

    @NotNull
    @Column(name = "modified_by", nullable = false)
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
}
