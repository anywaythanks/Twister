package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.GeneralAccountNotRegisteredException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@InformationService
@RequiredArgsConstructor
public class GeneralAccountInformationService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountMapper generalAccountMapper;

    @PreAuthorize("@generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, " +
            "@generalAccountMapperImpl.toName(#nameDto))")
    public GeneralAccountPartialResponseDto getPartial(GeneralAccountNameRequestDto nameDto) {
        GeneralAccountName name = generalAccountMapper.toName(nameDto);
        GeneralAccount generalAccount = generalAccountRepository.findByName(name)
                .orElseThrow(NotFoundException::new);
        return generalAccountMapper.toPartialDTO(generalAccount);
    }

    @PostAuthorize("returnObject.uuid == authentication.principal.uuid")
    public GeneralAccountIdAndUuidDto getId(GeneralAccountNameRequestDto nameDto) {
        GeneralAccountName name = generalAccountMapper.toName(nameDto);
        GeneralAccount generalAccount = generalAccountRepository.findByName(name)
                .orElseThrow(NotFoundException::new);
        return generalAccountMapper.toIdDTO(generalAccount);
    }

    @PostAuthorize("returnObject.uuid == authentication.principal.uuid")
    public GeneralAccountFullDto getFull(GeneralAccountNameRequestDto nameDto) {
        GeneralAccountName name = generalAccountMapper.toName(nameDto);
        GeneralAccount generalAccount = generalAccountRepository.findByName(name)
                .orElseThrow(NotFoundException::new);
        return generalAccountMapper.toFull(generalAccount);
    }

    @PreAuthorize("authentication.principal.uuid == #user.uuid")
    public GeneralAccountNameResponseDto getName(UserPrincipal user) {
        GeneralAccount generalAccount = generalAccountRepository.findByUserUuid(user.getUuid())
                .orElseThrow(GeneralAccountNotRegisteredException::new);
        return generalAccountMapper.toName(generalAccount);
    }

    public GeneralAccountPublicResponseDto getPublic(GeneralAccountNicknameRequestDto nickname) {
        GeneralAccount generalAccount = generalAccountRepository.findByNickname(nickname.getNickname())
                .orElseThrow(NotFoundException::new);
        return generalAccountMapper.toPublicDTO(generalAccount);
    }

    public GeneralAccountPublicResponseDto getPublic(GeneralAccountNameRequestDto name) {
        GeneralAccount generalAccount = generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElseThrow(NotFoundException::new);
        return generalAccountMapper.toPublicDTO(generalAccount);
    }
}
