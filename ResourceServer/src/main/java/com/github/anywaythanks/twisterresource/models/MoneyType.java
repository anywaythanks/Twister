package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "money_type")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class MoneyType {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "money_type_seq")
    @SequenceGenerator(name = "money_type_seq", sequenceName = "money_type_id_seq")
    Integer id;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    String name;

    @NotBlank
    @Length(min = 1, max = 64)
    @Column(nullable = false)
    String pathToIcon;
    @NotNull
    @Column(name = "modified_by", nullable = false)
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
}
