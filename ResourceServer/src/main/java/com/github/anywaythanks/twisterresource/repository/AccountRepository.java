package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(AccountNumber number);

    @Query("select a from Account a where a.number = :number and a.generalAccount.id = :gId")
    Optional<Account> findContaining(@Param("gId") Long generalAccountId, AccountNumber number);

    List<Account> findAllByGeneralAccountId(Long generalAccountId);
}