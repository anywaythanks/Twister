package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
public class ItemMoneyCreateRequestDto extends ItemCreateRequestDto implements Cost<MoneyCreateRequestDto> {
    @NonNull MoneyCreateRequestDto cost;

    public ItemMoneyCreateRequestDto(@NonNull String visibleName, @NonNull MoneyCreateRequestDto cost) {
        super(visibleName);
        this.cost = cost;
    }
}
