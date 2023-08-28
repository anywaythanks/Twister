package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemMoneyCreateRequestDto extends ItemCreateRequestDto implements Cost<MoneyCreateRequestDto> {
    @NonNull
    private MoneyCreateRequestDto cost;

    public ItemMoneyCreateRequestDto(@NonNull String visibleName, @NonNull MoneyCreateRequestDto cost) {
        super(visibleName);
        this.cost = cost;
    }
}
