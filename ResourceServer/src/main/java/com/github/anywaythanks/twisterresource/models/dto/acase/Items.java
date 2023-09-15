package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.SumEq;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.Percentage;

import java.util.List;

interface Items<T extends Percentage> {
    @SumEq(eqSum = "100")
    List<T> getItems();
}