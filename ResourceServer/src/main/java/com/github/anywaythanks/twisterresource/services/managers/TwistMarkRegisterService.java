package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkRegisterDto;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class TwistMarkRegisterService {
    private final TwistMarkRepository twistMarkRepository;
    private final CaseMapper caseMapper;
    private final GeneralAccountMapper generalAccountMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(TwistMarkRegisterDto registerDto) {
        Instant now = Instant.now();
        TwistMark twistMark = TwistMark.builder()
                .generalAccount(generalAccountMapper.toAccount(registerDto.getAccount()))
                .twistCase(caseMapper.toCase(registerDto.getTwistCase()))
                .consider(false)
                .createdOn(now)
                .updatedOn(now)
                .build();
        twistMarkRepository.save(twistMark);
    }
}
