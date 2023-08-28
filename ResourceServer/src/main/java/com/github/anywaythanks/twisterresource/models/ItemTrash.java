package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@PrimaryKeyJoinColumn(name = "item_trash_id")
@Table(name = "item_trash")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemTrash extends Item {
    public ItemTrash(@NonNull String name, @NonNull String visibleName) {
        super(name, visibleName);
    }
}
