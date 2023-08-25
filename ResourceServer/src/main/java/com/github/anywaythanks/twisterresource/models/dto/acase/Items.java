package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.validators.SumEq;

import java.util.List;

interface Items<T> {
    @SumEq(eqSum = "1", converter = Converter.class)
    List<T> getItems();
}
