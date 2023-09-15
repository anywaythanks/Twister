package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "items")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_id_seq")
    @Setter
    Long id;

    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
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
