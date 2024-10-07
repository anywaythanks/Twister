package com.github.anywaythanks.twisterresource.models.dto.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ItemMoneyCreateRequestDto.class, name = ItemTypes.Constants.MONEY_NAME),
        @JsonSubTypes.Type(value = ItemTrashCreateRequestDto.class, name = ItemTypes.Constants.TRASH_NAME)
})
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public sealed abstract class ItemCreateRequestDto implements VisibleName permits ItemMoneyCreateRequestDto, ItemTrashCreateRequestDto {
    @NonNull String visibleName;
}
