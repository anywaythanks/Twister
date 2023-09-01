package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.TwistMarkMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkRegisterDto;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@RegisterService
@RequiredArgsConstructor
public class TwistMarkRegisterService {
    private final TwistMarkRepository twistMarkRepository;
    private final CaseMapper caseMapper;
    private final GeneralAccountMapper generalAccountMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(TwistMarkRegisterDto registerDto) {
        GeneralAccount generalAccount = generalAccountMapper.toAccount(registerDto.getAccount());
        Case twistCase = caseMapper.toCase(registerDto.getTwistCase());
        Instant now = Instant.now();
        TwistMark twistMark = new TwistMark(twistCase, generalAccount, false, now, now);
        twistMarkRepository.save(twistMark);
    }
}
