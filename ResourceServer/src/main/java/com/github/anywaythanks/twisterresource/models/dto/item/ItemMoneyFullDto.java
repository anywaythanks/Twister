package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Getter
public class ItemMoneyFullDto extends ItemFullDto implements Cost<MoneyFullDto> {
    @NonNull MoneyFullDto cost;

    public ItemMoneyFullDto(@NonNull Long id, @NonNull String name, @NonNull String visibleName, @NonNull Instant createdOn, @NonNull Instant modifiedBy, @NonNull MoneyFullDto cost) {
        super(id, name, visibleName, createdOn, modifiedBy);
        this.cost = cost;
    }
}
