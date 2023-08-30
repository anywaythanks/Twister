package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterTwistMarkService {
    private final TwistMarkRepository twistMarkRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registerIfAbsent(TwistMark twistMark) {
        Optional<TwistMark> opt = twistMarkRepository.findFirstByGeneralAccountAndTwistCase(
                twistMark.getGeneralAccount(), twistMark.getTwistCase());
        if (opt.isEmpty()) {
            twistMarkRepository.save(twistMark);
        }
    }
}
