package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RegisterGeneralAccountService {
    @PreAuthorize("#userPrincipal.uuid == authentication.principal.uuid")
    GeneralAccountPartialResponseDto register(UserPrincipal userPrincipal, GeneralAccountCreateRequestDto create);

    @PreAuthorize("@generalAccountNameRepository.findById(#name.name).isEmpty() " +
            "or @generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, @generalAccountMapper.toName(#name)) " +
            "and #userPrincipal.uuid == authentication.principal.uuid")
    GeneralAccountPartialResponseDto merge(UserPrincipal userPrincipal, GeneralAccountNameRequestDto name,
                                           GeneralAccountCreateRequestDto create);
}
