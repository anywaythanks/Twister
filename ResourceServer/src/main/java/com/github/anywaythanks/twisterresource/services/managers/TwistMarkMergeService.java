package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.TwistMarkMapper;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkFullDto;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class TwistMarkMergeService {
    private final TwistMarkMapper twistMarkMapper;
    private final TwistMarkRepository twistMarkRepository;
    private final Clock clock;

    @Transactional
    public void merge(TwistMarkFullDto fullDto) {
        TwistMark twistMark = twistMarkMapper.toMark(fullDto);
        twistMark.setUpdatedOn(Instant.now(clock));
        twistMarkRepository.save(twistMark);
    }
}
