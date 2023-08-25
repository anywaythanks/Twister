package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.PagePartialResponseDto;

public interface CaseInformationService {
    CasePartialResponseDto getPartial(CaseNameRequestDto caseName);

    CasePartialWithoutCooldownResponseDto getPartialWithoutCooldown(CaseNameRequestDto caseName);

    PagePartialResponseDto<CaseLightPartialWithoutCooldownResponseDto> getPageWithoutCooldown(Integer page, Integer size);
}
