package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "item_trash_id")
@Table(name = "item_trash")
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
public class ItemTrash extends Item {
}
