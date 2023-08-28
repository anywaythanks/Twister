package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.exceptions.NotRegisterGeneralAccount;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneralAccountInformationService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountMapper generalAccountMapper;

    @PreAuthorize("@generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, @generalAccountMapperImpl.toName(#name))")
    public GeneralAccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name) {
        return generalAccountMapper.toPartialDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }

    @PostAuthorize("returnObject.uuid == authentication.principal.uuid")
    public GeneralAccountIdResponseDto getId(GeneralAccountNameRequestDto name) {
        return generalAccountMapper.toIdDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }

    @PreAuthorize("authentication.principal.uuid == #user.uuid")
    public GeneralAccountNameResponseDto getName(UserPrincipal user) {
        return generalAccountMapper.toName(generalAccountRepository.findByUserUuid(user.getUuid())
                .orElseThrow(NotRegisterGeneralAccount::new));
    }

    public GeneralAccountPublicResponseDto getPublic(GeneralAccountNicknameRequestDto nickname) {
        return generalAccountMapper.toPublicDTO(generalAccountRepository.findByNickname(nickname.getNickname())
                .orElseThrow(NotFoundException::new));
    }

    public GeneralAccountPublicResponseDto getPublic(GeneralAccountNameRequestDto name) {
        return generalAccountMapper.toPublicDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }
}
