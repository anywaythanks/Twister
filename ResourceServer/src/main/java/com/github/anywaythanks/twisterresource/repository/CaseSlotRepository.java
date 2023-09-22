package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseSlotRepository extends JpaRepository<CaseSlot<Item>, Long> {
    @EntityGraph(value = "CaseSlot.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<CaseSlot<Item>> findAllByOwnerCaseId(Long ownerCaseId);

    @EntityGraph(value = "CaseSlot.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<CaseSlot<Item>> findAllByOwnerCaseIdOrderByWinRate(Long ownerCaseId);
}
