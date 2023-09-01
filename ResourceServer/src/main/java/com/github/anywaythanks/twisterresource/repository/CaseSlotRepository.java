package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseSlotRepository extends JpaRepository<CaseSlot<Item>, Long> {
    List<CaseSlot<Item>> findAllByOwnerCaseId(Long ownerCaseId);
}
