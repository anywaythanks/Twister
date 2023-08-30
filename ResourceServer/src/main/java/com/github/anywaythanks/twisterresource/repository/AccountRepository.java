package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(AccountNumber number);
    @Query("select a from Account a where a.number = :number and a.generalAccount = :generalAccount")
    Optional<Account> findContaining(GeneralAccount generalAccount, AccountNumber number);
}