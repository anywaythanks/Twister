package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @EntityGraph(value = "Inventory.detail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Inventory> findByName(InventoryName name);

    @EntityGraph(value = "Inventory.detail", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select i.name from Inventory i where i.generalAccount.id = :generalId")
    List<InventoryName> findNames(@Param("generalId") Long generalId);

    @Query("select i from Inventory i where i.name = :name and i.generalAccount.id = :generalId")
    Optional<Inventory> findContaining(@Param("generalId")Long generalId,@Param("name") InventoryName name);
}