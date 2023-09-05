package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "twistes")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
public class Twist<T extends Item> {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "general_account_id", nullable = false)
    GeneralAccount generalAccount;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "number", nullable = false, unique = true)
    TwistNumber number;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    Case twistCase;
    @NotNull
    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    T wonItem;
    @Column(name = "quantity_item", nullable = false)
    Integer quantityItem;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
}