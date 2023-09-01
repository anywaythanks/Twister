package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemMoneyIdDto extends ItemIdDto {
    public ItemMoneyIdDto(@NonNull Long id) {
        super(id);
    }
}
