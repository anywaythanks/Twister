package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.Percentage;
import com.github.anywaythanks.twisterresource.validators.SumEq;

import java.util.List;

interface Items<T extends Percentage> {
    @SumEq(eqSum = "1")
    List<T> getItems();
}