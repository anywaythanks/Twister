package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @EntityGraph(value = "Inventory.detail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Inventory> findByName(InventoryName name);
}
