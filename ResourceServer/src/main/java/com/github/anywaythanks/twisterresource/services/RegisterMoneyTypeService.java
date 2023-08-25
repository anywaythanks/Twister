package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RegisterMoneyTypeService {
    @PreAuthorize("hasAuthority('ADMIN')")
    MoneyDTO.Type.Response.Partial merge(MoneyDTO.Type.Request.Name name, MoneyDTO.Type.Request.Create create);
}
