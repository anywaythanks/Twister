package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    <T extends Item> Optional<T> findByName(String name);
}