package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLastTwistResponseDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ActualCaseRepository {
    List<CaseLastTwistResponseDto> dates(Long generalAccountId, Long startId, Long endId, Sort sort);
}