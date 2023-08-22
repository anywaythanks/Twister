package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    <T extends Item> Optional<T> findByName(String name);
}