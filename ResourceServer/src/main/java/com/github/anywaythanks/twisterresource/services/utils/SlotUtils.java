package com.github.anywaythanks.twisterresource.services.utils;

import com.github.anywaythanks.twisterresource.exceptions.InvalidItemTypeException;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import org.springframework.stereotype.Service;

@Service
public class SlotUtils {
    public void addItems(Slot<?> slot, Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!slot.getItem().equals(item))
            throw new InvalidItemTypeException();
        slot.setQuantityItem(slot.getQuantityItem() + quantity);
    }

    public void removeItems(Slot<?> slot, Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!slot.getItem().equals(item))
            throw new InvalidItemTypeException();
        slot.setQuantityItem(slot.getQuantityItem() - quantity);
    }
}
