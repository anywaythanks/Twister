package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RegisterGeneralAccountService {
    @PreAuthorize("#userPrincipal.uuid == authentication.principal.uuid")
    GeneralAccountDTO.Response.Partial register(UserPrincipal userPrincipal, GeneralAccountDTO.Request.Create create);

    @PreAuthorize("@generalAccountNameRepository.findById(#name.name).isEmpty() " +
            "or @generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, @generalAccountMapper.toName(#name)) " +
            "and #userPrincipal.uuid == authentication.principal.uuid")
    GeneralAccountDTO.Response.Partial merge(UserPrincipal userPrincipal, GeneralAccountDTO.Request.Name name,
                                             GeneralAccountDTO.Request.Create create);
}
