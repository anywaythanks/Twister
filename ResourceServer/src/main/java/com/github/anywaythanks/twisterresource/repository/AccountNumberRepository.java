package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, String> {
}
