package ru.lexp00.platform.tptenders.services.responds;


import ru.lexp00.platform.tpcommon.dtos.responds.RespondDto;
import ru.lexp00.platform.tpcommon.dtos.responds.SystemRespondDto;
import ru.lexp00.platform.tptenders.entities.Respond;
import ru.lexp00.platform.tptenders.entities.Tender;

import java.util.List;
import java.util.UUID;

public interface RespondService {

    Respond findById(Long id);
    Respond save(Respond respond);
    Respond add(Tender tender, UUID userId, SystemRespondDto respond);

    List<Respond> findByUserId(UUID userId);
    List<RespondDto> findAllRespondByTenderId(Long tenderId);
}
