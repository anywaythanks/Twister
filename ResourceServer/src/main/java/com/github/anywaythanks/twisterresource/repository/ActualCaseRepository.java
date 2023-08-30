package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLastTwistResponseDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ActualCaseRepository {
    List<CaseLastTwistResponseDto> dates(GeneralAccount generalAccount, Long startId, Long endId, Sort sort);
}