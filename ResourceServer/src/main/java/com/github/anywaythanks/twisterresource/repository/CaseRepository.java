package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case, Long>, ActualCaseRepository {
    @EntityGraph(value = "Case.detail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Case> findByName(String name);

    @Override
    @EntityGraph(value = "Case.detail", type = EntityGraph.EntityGraphType.LOAD)
    Page<Case> findAll(Pageable pageable);
}
