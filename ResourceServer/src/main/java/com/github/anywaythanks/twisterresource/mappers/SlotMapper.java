package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper(uses = ItemMapper.class, componentModel = "spring")
public interface SlotMapper {
    @Mappings({
            @Mapping(source = "slot.item", target = "item"),
            @Mapping(source = "slot.quantityItem", target = "quantity")
    })
    SlotPartialResponseDto toPartialDTO(Slot<? extends Item> slot);

    SlotIdResponseDto toIdsDTO(Slot<?> slot);

    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotTransferRequestDto toTransfer(Slot<?> slot);

    @Mapping(source = "quantity.quantity", target = "quantity")
    SlotTransferRequestDto toTransfer(SlotQuantityRequestDto quantity, SlotIdResponseDto slot);

    default List<SlotPartialResponseDto> toPartialsDto(Set<InventorySlot<?>> set) {
        return set.stream().map(this::toPartialDTO).toList();
    }
}