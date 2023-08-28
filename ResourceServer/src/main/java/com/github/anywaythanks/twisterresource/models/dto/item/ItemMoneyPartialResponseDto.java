package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemMoneyPartialResponseDto extends ItemPartialResponseDto implements Cost<MoneyPartialResponseDto> {
    @NonNull
    MoneyPartialResponseDto cost;

    public ItemMoneyPartialResponseDto(@NonNull ItemTypes type, @NonNull String name, @NonNull String visibleName, @NonNull MoneyPartialResponseDto cost) {
        super(type, name, visibleName);
        this.cost = cost;
    }
}
