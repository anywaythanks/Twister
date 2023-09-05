package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GeneralAccountRepository extends JpaRepository<GeneralAccount, Long> {

    @EntityGraph(value = "GeneralAccount.detail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<GeneralAccount> findByName(GeneralAccountName name);

    @EntityGraph(value = "GeneralAccount.detail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<GeneralAccount> findByUserUuid(String userUuid);

    @EntityGraph(value = "GeneralAccount.detail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<GeneralAccount> findByNickname(String nickname);

    @Query("select case when (count(a) > 0) then true else false end " +
            "from GeneralAccount a where a.userUuid = :uuid and a.name = :name")
    boolean isAccountBelongsUser(
            @Param("uuid") String userUuid,
            @Param("name") GeneralAccountName accountName);

    @Query("select case when (count(a) > 0) then true else false end " +
            "from GeneralAccount a where a.userUuid = :uuid and a.id = :id")
    boolean isAccountBelongsUser(
            @Param("uuid") String userUuid,
            @Param("id") Long id);
}
