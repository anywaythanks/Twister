package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
}
