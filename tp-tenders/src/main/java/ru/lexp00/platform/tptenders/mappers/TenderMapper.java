package ru.lexp00.platform.tptenders.mappers;


import ru.lexp00.platform.tpcommon.dtos.tenders.LightTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderDto;
import ru.lexp00.platform.tptenders.entities.Tender;

public interface TenderMapper {
    TenderDto toDto(Tender tender);
    LightTenderDto toLightDto(Tender tender);
}
