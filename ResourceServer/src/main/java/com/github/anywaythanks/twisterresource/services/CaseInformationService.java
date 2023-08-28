package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;

public interface CaseInformationService {
    CasePartialResponseDto getPartial(CaseNameRequestDto caseName);

    CasePartialWithoutCooldownResponseDto getPartialWithoutCooldown(CaseNameRequestDto caseName);

    CaseWithoutCooldownPagePartialResponseDto getPageWithoutCooldown(Integer page, Integer size);
}
