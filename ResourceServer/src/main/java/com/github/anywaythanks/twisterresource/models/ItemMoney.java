package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.models.interfaces.SellingItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@PrimaryKeyJoinColumn(name = "item_money_id")
@Table(name = "item_money")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemMoney extends Item implements SellingItem {

    public ItemMoney(@NonNull String name, @NonNull String visibleName, @NonNull Money cost) {
        super(name, visibleName);
        this.cost = cost;
    }

    @NotNull
    @Column(nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "cost")),
    })
    @NonNull
    Money cost;
}
