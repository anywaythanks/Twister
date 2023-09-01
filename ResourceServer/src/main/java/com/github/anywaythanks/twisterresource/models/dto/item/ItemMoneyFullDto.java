package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemMoneyFullDto extends ItemFullDto implements Cost<MoneyFullDto> {
    public ItemMoneyFullDto(@NonNull Long id, @NonNull String name, @NonNull String visibleName, @NonNull Instant createdOn, @NonNull Instant modifiedBy, @NonNull MoneyFullDto cost) {
        super(id, name, visibleName, createdOn, modifiedBy);
        this.cost = cost;
    }

    @NonNull
    MoneyFullDto cost;
}
