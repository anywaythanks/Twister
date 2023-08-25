package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public interface GeneralAccountInformationService {
    @PreAuthorize("@generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, @generalAccountMapper.toName(#name))")
    GeneralAccountDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name);

    @PostAuthorize("returnObject.uuid == authentication.principal.uuid")
    GeneralAccountDTO.Response.Id getId(GeneralAccountDTO.Request.Name name);

    @PreAuthorize("authentication.principal.uuid == #user.uuid")
    GeneralAccountDTO.Response.Name getName(UserPrincipal user);

    GeneralAccountDTO.Response.Public getPublic(GeneralAccountDTO.Request.Nickname nickname);

    GeneralAccountDTO.Response.Public getPublic(GeneralAccountDTO.Request.Name name);
}
