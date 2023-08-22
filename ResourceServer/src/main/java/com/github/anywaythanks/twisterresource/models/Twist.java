package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "twistes")
public class Twist<T extends Item> {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_account_id", nullable = false)
    GeneralAccount generalAccount;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "number", nullable = false, unique = true)
    TwistNumber number;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
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

    protected Twist() {
    }

    public Twist(Account account, Case twistCase, T wonItem, Integer quantityItem, Instant createdOn, GeneralAccount generalAccount) {
        this.account = account;
        this.twistCase = twistCase;
        this.wonItem = wonItem;
        this.quantityItem = quantityItem;
        this.createdOn = createdOn;
        this.number = new TwistNumber();
        this.generalAccount = generalAccount;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public Case getTwistCase() {
        return twistCase;
    }

    public Item getWonItem() {
        return wonItem;
    }

    public Integer getQuantityItem() {
        return quantityItem;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public TwistNumber getNumber() {
        return number;
    }

    public GeneralAccount getGeneralAccount() {
        return generalAccount;
    }
}