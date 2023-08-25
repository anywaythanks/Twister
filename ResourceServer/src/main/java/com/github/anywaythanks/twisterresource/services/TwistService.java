package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.*;

import java.security.NoSuchAlgorithmException;

public interface TwistService {
    TwistDTO.Response.Partial twist(GeneralAccountDTO.Request.Name name,
                                    InventoryDTO.Request.Name nameInventory,
                                    AccountDTO.Request.Number number,
                                    CaseDTO.Request.Name caseName) throws NoSuchAlgorithmException;
}
