package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @NonNull
    @JoinColumn(name = "number", nullable = false, unique = true)
    AccountNumber number;

    @NotNull
    @Column(nullable = false)
    @Embedded
    @NonNull
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "amount", nullable = false))
    })
    Money amount;
}