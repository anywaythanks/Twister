package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.exceptions.NotRegisterGeneralAccount;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneralAccountInformationServiceImpl implements GeneralAccountInformationService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountMapper generalAccountMapper;

    public GeneralAccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name) {
        return generalAccountMapper.toPartialDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }

    public GeneralAccountIdResponseDto getId(GeneralAccountNameRequestDto name) {
        return generalAccountMapper.toIdDTO(generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new));
    }

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
