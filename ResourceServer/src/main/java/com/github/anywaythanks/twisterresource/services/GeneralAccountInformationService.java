package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public interface GeneralAccountInformationService {
    @PreAuthorize("@generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, @generalAccountMapper.toName(#name))")
    GeneralAccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name);

    @PostAuthorize("returnObject.uuid == authentication.principal.uuid")
    GeneralAccountIdResponseDto getId(GeneralAccountNameRequestDto name);

    @PreAuthorize("authentication.principal.uuid == #user.uuid")
    GeneralAccountNameResponseDto getName(UserPrincipal user);

    GeneralAccountPublicResponseDto getPublic(GeneralAccountNicknameRequestDto nickname);

    GeneralAccountPublicResponseDto getPublic(GeneralAccountNameRequestDto name);
}
