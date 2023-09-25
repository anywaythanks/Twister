package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.PutService;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountRegisterDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PutService
@RequiredArgsConstructor
public class GeneralAccountPutService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRegisterService generalAccountRegisterService;
    private final GeneralAccountMergeService generalAccountMergeService;

    @PreAuthorize("#userPrincipal.uuid == authentication.principal.uuid " +
            "and ((@generalAccountNameRepository.findById(#nameDto.name).isEmpty() " +
            "and @generalAccountRepository.findByUserUuid(#userPrincipal.uuid).isEmpty())" +
            "or (@generalAccountNameRepository.findById(#nameDto.name).isPresent() " +
            "and @generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, " +
            "@generalAccountNameRepository.findById(#nameDto.name).get())))")
    @Transactional
    public GeneralAccountPartialResponseDto put(UserPrincipal userPrincipal, GeneralAccountNameRequestDto nameDto,
                                                GeneralAccountCreateRequestDto create) {
        GeneralAccountName name = generalAccountMapper.toName(nameDto);
        Optional<GeneralAccount> optionalGeneralAccount = generalAccountRepository
                .findByName(name);
        if (optionalGeneralAccount.isEmpty()) {
            GeneralAccountRegisterDto registerDto = generalAccountMapper.toRegister(userPrincipal, name, create);
            return generalAccountRegisterService.register(registerDto);
        }
        GeneralAccount generalAccount = optionalGeneralAccount.get();
        generalAccount.setNickname(create.getNickname());
        return generalAccountMergeService.merge(generalAccountMapper.toFull(generalAccount));
    }
}
