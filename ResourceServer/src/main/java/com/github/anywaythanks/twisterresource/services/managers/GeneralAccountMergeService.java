package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.exceptions.NicknameUniqueException;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class GeneralAccountMergeService {
    private final GeneralAccountRepository generalAccountRepository;
    private final GeneralAccountMapper generalAccountMapper;

    @PreAuthorize("#mergeDto.uuid == authentication.principal.uuid ")
    //+ "and @generalAccountRepository.isAccountBelongsUser(authentication.principal.uuid, #mergeDto.id)")
    @Transactional
    public GeneralAccountPartialResponseDto merge(GeneralAccountFullDto fullDto) {
        generalAccountRepository.findByNickname(fullDto.getNickname()).ifPresent(account -> {
            if (!account.getName().getName().equals(fullDto.getName()))
                throw new NicknameUniqueException();
        });
        GeneralAccount generalAccount = generalAccountMapper.toAccount(fullDto);
        generalAccount.setModifiedBy(Instant.now());
        GeneralAccount resultAccount = generalAccountRepository.save(generalAccount);
        return generalAccountMapper.toPartialDTO(resultAccount);
    }
}
