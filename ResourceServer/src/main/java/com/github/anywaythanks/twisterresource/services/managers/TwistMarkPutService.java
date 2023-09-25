package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.PutService;
import com.github.anywaythanks.twisterresource.mappers.TwistMarkMapper;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkPutDto;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PutService
@RequiredArgsConstructor
public class TwistMarkPutService {
    private final TwistMarkMapper twistMarkMapper;
    private final TwistMarkRepository twistMarkRepository;
    private final TwistMarkRegisterService twistMarkRegisterService;
    private final TwistMarkMergeService twistMarkMergeService;

    @Transactional
    public void putIfAbsent(TwistMarkPutDto putDto) {
        Optional<TwistMark> optionalTwistMark = twistMarkRepository
                .findFirstByGeneralAccountIdAndTwistCaseId(putDto.getAccount().getId(), putDto.getTwistCase().getId());
        if (optionalTwistMark.isEmpty()) {
            twistMarkRegisterService.register(twistMarkMapper.toRegister(putDto));
        }
    }
}
