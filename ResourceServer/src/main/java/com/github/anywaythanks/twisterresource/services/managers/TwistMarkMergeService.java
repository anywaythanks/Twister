package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.TwistMarkMapper;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkMergeDto;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class TwistMarkMergeService {
    private final TwistMarkMapper twistMarkMapper;
    private final TwistMarkRepository twistMarkRepository;

    @Transactional
    public void merge(TwistMarkMergeDto mergeDto) {
        TwistMark twistMark = twistMarkMapper.toMark(mergeDto);
        twistMark.setUpdatedOn(Instant.now());
        twistMarkRepository.save(twistMark);
    }
}
