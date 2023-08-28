package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCooldownIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;

public interface CaseActualInformationService {
    CaseCooldownIdResponseDto getCooldownId(GeneralAccountNameRequestDto name,
                                            CaseNameRequestDto caseName);

    CasePartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                      CaseNameRequestDto caseName);

    CasePagePartialResponseDto getPage(int page, int size, GeneralAccountNameRequestDto name);
}
