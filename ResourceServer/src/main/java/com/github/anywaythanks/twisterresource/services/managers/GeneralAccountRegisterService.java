package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.exceptions.GeneralAccountExistsException;
import com.github.anywaythanks.twisterresource.exceptions.NicknameUniqueException;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountRegisterDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountNameRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@RegisterService
@Transactional
@RequiredArgsConstructor
public class GeneralAccountRegisterService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountNameRepository generalAccountNameRepository;
    private final GeneralAccountMapper generalAccountMapper;
    private final Clock clock;

    @PreAuthorize("#generalAccountRegisterDto.uuid == authentication.principal.uuid")
    @Transactional
    public GeneralAccountPartialResponseDto register(GeneralAccountRegisterDto generalAccountRegisterDto) {
        generalAccountRepository.findByUserUuid(generalAccountRegisterDto.getUuid()).ifPresent(account -> {
            throw new GeneralAccountExistsException(account);
        });
        generalAccountRepository.findByNickname(generalAccountRegisterDto.getNickname()).ifPresent(account -> {
            throw new NicknameUniqueException();
        });
        GeneralAccountName name = generalAccountMapper.toName(generalAccountRegisterDto.getName());
        Instant now = Instant.now(clock);
        var account = GeneralAccount.builder()
                .userUuid(generalAccountRegisterDto.getUuid())
                .name(generalAccountNameRepository.save(name))
                .nickname(generalAccountRegisterDto.getNickname())
                .modifiedBy(now)
                .createdOn(now)
                .build();
        return generalAccountMapper.toPartialDTO(generalAccountRepository.save(account));
    }

    @PreAuthorize("#userPrincipal.uuid == authentication.principal.uuid")
    @Transactional
    public GeneralAccountPartialResponseDto register(UserPrincipal userPrincipal, GeneralAccountCreateRequestDto create) {
        GeneralAccountName name = GeneralAccountName.builder().build();
        GeneralAccountName newName = generalAccountNameRepository.save(name);
        return register(generalAccountMapper.toRegister(userPrincipal, newName, create));
    }
}
