package ru.lexp00.platform.tptenders.mappers;

import ru.lexp00.platform.tptenders.entities.Respond;
import ru.lexp00.platform.tpcommon.dtos.responds.RespondDto;

public interface RespondMapper {

    RespondDto toDto(Respond respond);
}