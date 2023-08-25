package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface ActualCaseRepository {
    List<? extends Map.Entry<Long, Instant>> dates(GeneralAccount generalAccount, Long startId, Long endId);
}
