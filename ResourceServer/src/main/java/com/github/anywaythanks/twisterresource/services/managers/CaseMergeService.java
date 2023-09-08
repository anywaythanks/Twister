package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseFullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class CaseMergeService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final Clock clock;
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public CasePartialResponseDto merge(CaseFullDto caseMergeDto) {
        Case mergedCase = caseMapper.toCase(caseMergeDto);
        mergedCase.setModifiedBy(Instant.now(clock));
        Case resultCase = caseRepository.save(mergedCase);
        return caseMapper.toPartialDTO(resultCase);
    }

}
