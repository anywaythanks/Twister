package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.BatchSize;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "number", nullable = false, unique = true)
    AccountNumber number;

    @NotNull
    @Column(nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "amount", nullable = false))
    })
    Money amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinTable(name = "account_slot_mapping",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "slot_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item")
    Map<Item, AccountSlot<?>> accountSlotMap = new HashMap<>();

    protected Account() {
    }

    public Account(Money amount) {
        this.amount = amount;
    }

    public Account(AccountNumber number, Money amount) {
        this.number = number;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountNumber getNumber() {
        return number;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public void setNumber(AccountNumber number) {
        this.number = number;
    }

    public Map<Item, AccountSlot<?>> getAccountSlotMap() {
        return accountSlotMap;
    }
}