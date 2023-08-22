package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "account_slots")
public class AccountSlot<T extends Item> extends Slot<T> {
    protected AccountSlot() {

    }

//    @NotNull
//    @ManyToOne
//    @JoinColumn(nullable = false, updatable = false, insertable = false)
//    Account account;

    public AccountSlot(T item, Integer quantityItem) {
        super(item, quantityItem);
    }
}
