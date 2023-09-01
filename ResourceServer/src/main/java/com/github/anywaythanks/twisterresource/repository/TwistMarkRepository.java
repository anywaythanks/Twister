package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TwistMarkRepository extends JpaRepository<TwistMark, Long> {
    Optional<TwistMark> findFirstByGeneralAccountIdAndTwistCaseId(Long generalAccountId, Long twistCaseId);
}
