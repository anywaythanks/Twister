package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Entity
@PrimaryKeyJoinColumn(name = "item_trash_id")
@Table(name = "item_trash")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemTrash extends Item {
    public ItemTrash(@NonNull String name, @NonNull String visibleName,@NonNull Instant modifiedBy,@NonNull Instant createdOn) {
        super(name, visibleName, modifiedBy, createdOn);
    }
}
