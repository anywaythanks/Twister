package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkMergeDto;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkPutDto;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkRegisterDto;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface TwistMarkMapper {
    TwistMark toMark(TwistMarkMergeDto twistMarkMergeDto);
    TwistMarkMergeDto toMerge(TwistMark twistMark);
    TwistMarkRegisterDto toRegister(TwistMarkPutDto mergeDto);
}
