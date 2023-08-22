package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "item_trash_id")
@Table(name = "item_trash")
public class ItemTrash extends Item {
    protected ItemTrash() {

    }

    public ItemTrash(String name, String visibleName) {
        setName(name);
        setVisibleName(visibleName);
    }
}
