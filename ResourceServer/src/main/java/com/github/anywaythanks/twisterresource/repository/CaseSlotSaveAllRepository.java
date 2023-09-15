package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;

import java.util.Collection;
import java.util.List;

public interface CaseSlotSaveAllRepository {
    List<CaseSlot<Item>> saveAll(Case caseOwner, Collection<CaseSlot<Item>> slots);
}
