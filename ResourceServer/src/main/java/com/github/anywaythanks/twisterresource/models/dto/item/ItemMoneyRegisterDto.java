package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Getter
public class ItemMoneyRegisterDto extends ItemRegisterDto implements Cost<MoneyFullDto> {
    @NonNull MoneyFullDto cost;

    public ItemMoneyRegisterDto(@NonNull String name, @NonNull String visibleName, @NonNull MoneyFullDto cost) {
        super(name, visibleName);
        this.cost = cost;
    }
}
