package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;

import java.security.NoSuchAlgorithmException;

public interface TwistService {
    TwistPartialResponseDto twist(GeneralAccountNameRequestDto name,
                                  InventoryNameRequestDto nameInventory,
                                  AccountNumberRequestDto number,
                                  CaseNameRequestDto caseName) throws NoSuchAlgorithmException;
}
