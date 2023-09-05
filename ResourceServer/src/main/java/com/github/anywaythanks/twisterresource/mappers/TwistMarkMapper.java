package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkFullDto;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkPutDto;
import com.github.anywaythanks.twisterresource.models.dto.twistMark.TwistMarkRegisterDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface TwistMarkMapper {
    @Mapping(source = "fullDto.account", target = "generalAccount")
    @Mapping(source = "fullDto.modifiedBy", target = "updatedOn")
    TwistMark toMark(TwistMarkFullDto fullDto);

    @InheritInverseConfiguration
    TwistMarkFullDto toFull(TwistMark twistMark);

    TwistMarkPutDto toPut(Boolean consider, GeneralAccountIdAndUuidDto account, Case twistCase);

    TwistMarkRegisterDto toRegister(TwistMarkPutDto putDto);
}
