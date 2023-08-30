package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Entity
@Table(name = "money_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Data
public class MoneyType {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    Integer id;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    @NonNull
    @EqualsAndHashCode.Include
    String name;

    @NotBlank
    @Length(min = 1, max = 64)
    @Column(nullable = false)
    @NonNull
    @EqualsAndHashCode.Exclude
    String pathToIcon;
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
