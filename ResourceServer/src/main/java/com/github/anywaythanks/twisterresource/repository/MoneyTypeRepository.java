package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MoneyTypeRepository extends JpaRepository<MoneyType, Integer> {
    @Query("from MoneyType tm where tm.name = :name")
    Optional<MoneyType> findByName(@Param("name") String name);
}
