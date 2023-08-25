package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.GeneralAccountExistsException;
import com.github.anywaythanks.twisterresource.exceptions.UniqueException;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountNameRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.services.RegisterGeneralAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterGeneralAccountServiceImpl implements RegisterGeneralAccountService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountNameRepository generalAccountNameRepository;
    private final GeneralAccountMapper generalAccountMapper;

    public RegisterGeneralAccountServiceImpl(GeneralAccountRepository generalAccountRepository,
                                             GeneralAccountNameRepository generalAccountNameRepository,
                                             GeneralAccountMapper generalAccountMapper) {
        this.generalAccountRepository = generalAccountRepository;
        this.generalAccountNameRepository = generalAccountNameRepository;
        this.generalAccountMapper = generalAccountMapper;
    }

    public GeneralAccountPartialResponseDto register(UserPrincipal userPrincipal, GeneralAccountCreateRequestDto create) {
        generalAccountRepository.findByUserUuid(userPrincipal.getUuid()).ifPresent(account -> {
            throw new GeneralAccountExistsException(account);
        });
        generalAccountRepository.findByNickname(create.getNickname()).ifPresent(account -> {
            throw new UniqueException();//TODO: throws
        });
        var account = new GeneralAccount(userPrincipal.getUuid(), create.getNickname(), new GeneralAccountName());
        return generalAccountMapper.toPartialDTO(generalAccountRepository.save(account));
    }


    public GeneralAccountPartialResponseDto merge(UserPrincipal userPrincipal, GeneralAccountNameRequestDto name,
                                                  GeneralAccountCreateRequestDto create) {
        var generalAccount = generalAccountMapper.toAccount(userPrincipal.getUuid(), name, create);
        generalAccountRepository.findByUserUuid(generalAccount.getUserUuid()).ifPresent(account -> {
            if (!account.getName().equals(generalAccount.getName()))
                throw new UniqueException();//TODO: throws
        });
        generalAccountRepository.findByNickname(generalAccount.getNickname()).ifPresent(account -> {
            if (!account.getName().equals(generalAccount.getName()))
                throw new UniqueException();//TODO: throws
        });

        var persistenceAccount = generalAccountRepository.findByName(generalAccountMapper.toName(name))
                .orElse(generalAccount);
        persistenceAccount.setName(generalAccountNameRepository.save(persistenceAccount.getName()));
        persistenceAccount.setNickname(generalAccount.getNickname());
        return generalAccountMapper.toPartialDTO(generalAccountRepository.save(persistenceAccount));
    }
}
