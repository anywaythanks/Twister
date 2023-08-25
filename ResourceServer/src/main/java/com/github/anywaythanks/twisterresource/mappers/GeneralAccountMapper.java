package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.dto.general.*;

public interface GeneralAccountMapper {

     GeneralAccountPartialResponseDto toPartialDTO(GeneralAccount account);

     GeneralAccountIdResponseDto toIdDTO(GeneralAccount account);

     GeneralAccountPublicResponseDto toPublicDTO(GeneralAccount account);

     GeneralAccount toAccount(String uuid, GeneralAccountNameRequestDto name,
                                    GeneralAccountCreateRequestDto request);

     Long toId(GeneralAccountIdResponseDto id);

     GeneralAccountName toName(GeneralAccountNameRequestDto name);

     GeneralAccountNameResponseDto toName(GeneralAccount account);

}