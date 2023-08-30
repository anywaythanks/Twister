package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor

@Getter
public class Account {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @NonNull
    @JoinColumn(name = "number", nullable = false, unique = true)
    @Setter
    AccountNumber number;

    @NotNull
    @Column(nullable = false)
    @Embedded
    @NonNull
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "amount", nullable = false))
    })
    @Setter
    Money amount;

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