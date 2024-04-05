package ru.lexp00.platform.tptenders.services.tenders;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import ru.lexp00.platform.tpcommon.dtos.responds.SystemRespondDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.LightTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.SystemTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderDto;
import ru.lexp00.platform.tpcommon.dtos.users.ContractorDto;
import ru.lexp00.platform.tptenders.entities.Tender;

import java.util.List;
import java.util.UUID;

public interface TendersService {
    Page<LightTenderDto> findAll(Specification<Tender> spec, int pageNumber, int pageSize);
    List<LightTenderDto> findByUserId(UUID userId);
    List<LightTenderDto> findAllTendersByUserIdWhereRespondIs(UUID id);
    TenderDto findById(Long id);
    ResponseEntity<?> save(SystemTenderDto systemTenderDto);
    ResponseEntity<?> announceTender(Long id, UUID userId);
    ResponseEntity<?> delete(Long id, UUID userId);
    ResponseEntity<?> closeTender(Long id);
    ResponseEntity<?> setContractor(ContractorDto contractorDto);
    ResponseEntity<?> rejectContractor(ContractorDto contractorDto);
    ResponseEntity<?> addRespond(SystemRespondDto systemRespondDto);

}
