package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapstructConfig.class)
public interface TwistMapper {
    @Mappings({
            @Mapping(source = "wonSlot.name.name", target = "wonSlotName"),
            @Mapping(source = "twist.wonItem", target = "item"),
            @Mapping(source = "twist.createdOn", target = "twistedOn"),
            @Mapping(source = "twist.twistCase", target = "selectCase"),
            @Mapping(source = "twist.quantityItem", target = "quantity")
    })
    TwistPartialResponseDto toDTO(CaseSlot<?> wonSlot, Twist<? extends Item> twist);
}
