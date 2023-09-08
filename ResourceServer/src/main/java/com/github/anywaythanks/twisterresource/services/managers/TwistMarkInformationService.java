package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.TwistMarkMapper;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkFullDto;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;

@InformationService
@RequiredArgsConstructor
public class TwistMarkInformationService {
    private final TwistMarkRepository twistMarkRepository;
    private final TwistMarkMapper twistMarkMapper;

    public TwistMarkFullDto getFull(GeneralAccountIdAndUuidDto generalAccountIdAndUuidDto, CaseIdDto caseIdDto) {
        TwistMark twistMark = twistMarkRepository
                .findFirstByGeneralAccountIdAndTwistCaseId(generalAccountIdAndUuidDto.getId(), caseIdDto.getId())
                .orElseThrow(NotFoundException::new);
        return twistMarkMapper.toFull(twistMark);
    }
}
