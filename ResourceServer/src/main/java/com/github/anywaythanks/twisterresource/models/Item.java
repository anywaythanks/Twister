package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Item {
    @Id
    @GeneratedValue(generator = "ITEM_ID_GENERATOR")
    Long id;

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
    @Setter
    String visibleName;

//    String picturePath; //TODO
}
