package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.models.interfaces.SellingItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@PrimaryKeyJoinColumn(name = "item_money_id")
@Table(name = "item_money")
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
public class ItemMoney extends Item implements SellingItem {
    @NotNull
    @Column(nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "cost")),
    })
    @Setter
    Money cost;
}
