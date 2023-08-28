package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "twistes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class Twist<T extends Item> {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @NonNull
    Account account;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_account_id", nullable = false)
    @NonNull
    GeneralAccount generalAccount;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "number", nullable = false, unique = true)
    @NonNull
    TwistNumber number;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    @NonNull
    Case twistCase;
    @NotNull
    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    T wonItem;
    @Column(name = "quantity_item", nullable = false)
    @NonNull
    Integer quantityItem;
    @NotNull
    @Column(name = "created_on", nullable = false)
    @NonNull
    Instant createdOn;
}