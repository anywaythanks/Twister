package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Getter
@EqualsAndHashCode(callSuper = true)
public class ItemMoneyPartialResponseDto extends ItemPartialResponseDto implements Cost<MoneyPartialResponseDto> {
    @NonNull MoneyPartialResponseDto cost;

    public ItemMoneyPartialResponseDto(@NonNull ItemTypes type, @NonNull String name, @NonNull String visibleName, @NonNull MoneyPartialResponseDto cost) {
        super(type, name, visibleName);
        this.cost = cost;
    }
}
