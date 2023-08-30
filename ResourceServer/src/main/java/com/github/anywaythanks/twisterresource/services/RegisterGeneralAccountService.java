package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.GeneralAccountExistsException;
import com.github.anywaythanks.twisterresource.exceptions.NicknameUniqueException;
import com.github.anywaythanks.twisterresource.exceptions.UniqueException;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountNameRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterGeneralAccountService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountNameRepository generalAccountNameRepository;
    private final GeneralAccountMapper generalAccountMapper;

    @PreAuthorize("#userPrincipal.uuid == authentication.principal.uuid")
    @Transactional
    public GeneralAccountPartialResponseDto register(UserPrincipal userPrincipal, GeneralAccountCreateRequestDto create) {
        generalAccountRepository.findByUserUuid(userPrincipal.getUuid()).ifPresent(account -> {
            throw new GeneralAccountExistsException(account);
        });
        generalAccountRepository.findByNickname(create.getNickname()).ifPresent(account -> {
            throw new NicknameUniqueException();
        });
        var account = new GeneralAccount(userPrincipal.getUuid(), new GeneralAccountName(), create.getNickname());
        return generalAccountMapper.toPartialDTO(generalAccountRepository.save(account));
    }

    @PreAuthorize("#userPrincipal.uuid == authentication.principal.uuid " +
            "and ((@generalAccountNameRepository.findById(#nameDto.name).isEmpty() " +
            "and @generalAccountRepository.findByUserUuid(#userPrincipal.uuid).isEmpty())" +
            "or (@generalAccountNameRepository.findById(#nameDto.name).isPresent() " +
            "and @generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, " +
            "@generalAccountNameRepository.findById(#nameDto.name).get())))")
    @Transactional
    public GeneralAccountPartialResponseDto merge(UserPrincipal userPrincipal, GeneralAccountNameRequestDto nameDto,
                                                  GeneralAccountCreateRequestDto create) {
        GeneralAccount mergedAccount = generalAccountMapper.toAccount(userPrincipal.getUuid(), nameDto, create);
        generalAccountRepository.findByNickname(mergedAccount.getNickname()).ifPresent(account -> {
            if (!account.getName().equals(mergedAccount.getName()))
                throw new NicknameUniqueException();
        });
        GeneralAccountName accountName = generalAccountMapper.toName(nameDto);
        var persistenceAccount = generalAccountRepository.findByName(accountName)
                .orElse(mergedAccount);
        persistenceAccount.setName(generalAccountNameRepository.save(persistenceAccount.getName()));
        persistenceAccount.setNickname(mergedAccount.getNickname());
        return generalAccountMapper.toPartialDTO(generalAccountRepository.save(persistenceAccount));
    }
}
