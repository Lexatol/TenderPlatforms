package ru.lexp00.platform.tptenders.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lexp00.platform.tpcommon.dtos.specializations.SpecDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.LightTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderDto;
import ru.lexp00.platform.tptenders.entities.Tender;
import ru.lexp00.platform.tptenders.services.SpecializationClient;
import ru.lexp00.platform.tptenders.services.UserClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TenderMapperImpl implements TenderMapper {

    private final UserClient userClient;
    private final SpecializationClient specializationClient;

    @Override
    public TenderDto toDto(Tender tender) {
        String customer = getTitle(tender, "customer");
        String contractor = getTitle(tender, "contractor");
        SpecDto specDto = specializationClient.findById(tender.getSpecializationId());
        return TenderDto.builder()
                .tenderId(tender.getTenderId())
                .title(tender.getTitle())
                .dateStart(tender.getDateStart())
                .dateFinish(tender.getDateFinish())
                .address(tender.getAddress())
                .contractor(contractor)
                .customer(customer)
                .customerId(tender.getCustomerId())
                .description(tender.getDescription())
                .minPrice(tender.getMinPrice())
                .maxPrice(tender.getMaxPrice())
                .status(tender.getStatus().name())
                .specializationTitle(specDto.getTitle())
                .specializationId(tender.getSpecializationId())
                .createAt(tender.getCreateAt())
                .countResponds(tender.getResponds().size())
                .announceDate(tender.getAnnounceDate())
                .build();
    }

    @Override
    public LightTenderDto toLightDto(Tender tender) {
        SpecDto specDto = specializationClient.findById(tender.getSpecializationId());
        String customer = getTitle(tender, "customer");
        return LightTenderDto.builder()
                .tenderId(tender.getTenderId())
                .title(tender.getTitle())
                .customerName(customer)
                .customerId(tender.getCustomerId())
                .dateStart(tender.getDateStart())
                .dateFinish(tender.getDateFinish())
                .address(tender.getAddress())
                .description(tender.getDescription())
                .minPrice(tender.getMinPrice())
                .maxPrice(tender.getMaxPrice())
                .status(tender.getStatus().name())
                .specializationTitle(specDto.getTitle())
                .specializationId(tender.getSpecializationId())
                 .countResponds(tender.getResponds().size())
                .createAt(tender.getCreateAt())
                .announceDate(tender.getAnnounceDate())
                .build();
    }

    private String getTitle(Tender tender, String t) {
        String title = "";
        UUID id = null;
        if (t.equals("customer")) {
            id = tender.getCustomerId();
        }
        if (t.equals("contractor")) {
            if (tender.getContractorId() != null) {
                id = tender.getContractorId();
            }
        }
        if (id == null) {
            return title;
        }
        title = userClient.getUserById(id).getUserName();
        return title;
    }

}
