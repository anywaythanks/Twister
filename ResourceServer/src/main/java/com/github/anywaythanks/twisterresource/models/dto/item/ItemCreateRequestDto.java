package com.github.anywaythanks.twisterresource.models.dto.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ItemMoneyCreateRequestDto.class, name = ItemTypes.Constants.MONEY_NAME),
        @JsonSubTypes.Type(value = ItemTrashCreateRequestDto.class, name = ItemTypes.Constants.TRASH_NAME)
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public abstract class ItemCreateRequestDto implements VisibleName, Type {
    @NonNull
    protected String visibleName;
    protected ItemTypes type;
}
