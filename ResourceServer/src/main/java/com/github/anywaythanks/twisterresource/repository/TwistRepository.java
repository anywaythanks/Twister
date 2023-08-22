package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Twist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwistRepository extends JpaRepository<Twist<? extends Item>, Long> {
}
