package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.exceptions.NotRegisterGeneralAccount;
import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class GeneralAccountInformationService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountMapper generalAccountMapper;

    public GeneralAccountInformationService(GeneralAccountRepository generalAccountRepository,
                                            GeneralAccountMapper generalAccountMapper) {
        this.generalAccountRepository = generalAccountRepository;
        this.generalAccountMapper = generalAccountMapper;
    }

    @PreAuthorize("@generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, @generalAccountMapper.toName(#name))")
    public GeneralAccountDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name) {
        return generalAccountMapper.toPartialDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }

    @PostAuthorize("returnObject.uuid == authentication.principal.uuid")
    public GeneralAccountDTO.Response.Id getId(GeneralAccountDTO.Request.Name name) {
        return generalAccountMapper.toIdDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }

    @PreAuthorize("authentication.principal.uuid == #user.uuid")
    public GeneralAccountDTO.Response.Name getName(UserPrincipal user) {
        return generalAccountMapper.toName(generalAccountRepository.findByUserUuid(user.getUuid())
                .orElseThrow(NotRegisterGeneralAccount::new));
    }

    public GeneralAccountDTO.Response.Public getPublic(GeneralAccountDTO.Request.Nickname nickname) {
        return generalAccountMapper.toPublicDTO(generalAccountRepository.findByNickname(nickname.getNickname())
                .orElseThrow(NotFoundException::new));
    }

    public GeneralAccountDTO.Response.Public getPublic(GeneralAccountDTO.Request.Name name) {
        return generalAccountMapper.toPublicDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }
}
