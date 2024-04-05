package ru.lexp00.platform.tptenders.services.tenders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lexp00.platform.tpcommon.dtos.chat.NewMessage;
import ru.lexp00.platform.tpcommon.dtos.responds.SystemRespondDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.LightTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.SystemTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderDto;
import ru.lexp00.platform.tpcommon.dtos.users.ContractorDto;
import ru.lexp00.platform.tpcommon.dtos.users.UserDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpcommon.exceptions.ResponseError;
import ru.lexp00.platform.tptenders.entities.Respond;
import ru.lexp00.platform.tptenders.entities.Tender;
import ru.lexp00.platform.tptenders.enums.MsgTenders;
import ru.lexp00.platform.tptenders.enums.Status;
import ru.lexp00.platform.tptenders.mappers.RespondMapper;
import ru.lexp00.platform.tptenders.mappers.TenderMapper;
import ru.lexp00.platform.tptenders.repositories.TenderRepository;
import ru.lexp00.platform.tptenders.services.UserClient;
import ru.lexp00.platform.tptenders.services.responds.RespondService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

@Service
@RequiredArgsConstructor
@Slf4j
public class TendersServiceImpl implements TendersService {
    private final TenderRepository tenderRepository;

    private final RespondService respondService;

    private final TenderMapper tenderMapper;
    private final RespondMapper respondMapper;

    private UserClient userClient;
    private ChatClient chatClient;

    @Value("${lifeTime}")
    private Long lifeTimeTender;

    @Value("${password.adminId}")
    private UUID adminId;

//todo сделать свой page
    @Override
    public Page<LightTenderDto> findAll(Specification<Tender> spec, int pageNumber, int pageSize) {
        List<LightTenderDto> tenders = tenderRepository.findAll(spec)
                .stream()
                .filter(t -> !Objects.equals(t.getStatus(), Status.DRAFT) &&
                        !Objects.equals(t.getStatus(), Status.DELETED))
                .sorted(Comparator.comparing(Tender::getCreateAt).reversed())
                .map(tenderMapper::toLightDto)
                .collect(Collectors.toList());
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        var total = tenders.size();
        var start = toIntExact(pageRequest.getOffset());
        var end = Math.min((start + pageRequest.getPageSize()), total);
        List<LightTenderDto> output = new ArrayList<>();
        if (start <= end) {
            output = tenders.subList(start, end);
        }
        return new PageImpl<>(output, pageRequest, total);
    }

    @Override
    public List<LightTenderDto> findAllTendersByUserIdWhereRespondIs(UUID id) {
        List<Respond> respond = respondService.findByUserId(id);
        return respond.stream()
                .map(Respond::getTender)
                .map(tenderMapper::toLightDto).toList();
    }

    @Override
    public TenderDto findById(Long id) {
        var tender = findTenderById(id);
        return tenderMapper.toDto(tender);
    }

    @Override
    public List<LightTenderDto> findByUserId(UUID userId) {

        var user = userClient.getUserById(userId);
        return tenderRepository.findAllByCustomerId(user.getUserId())
                    .stream()
                    .filter(Objects::nonNull)
                    .map(tenderMapper::toLightDto)
                    .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> save(SystemTenderDto systemTenderDto) {

        var customer = userClient.getUserById(systemTenderDto.getUserId());
        var tender = createTender(systemTenderDto, customer.getUserId());

        var minPrice = new BigDecimal(String.valueOf(systemTenderDto.getMinPrice()));
        var maxPrice = new BigDecimal(String.valueOf(systemTenderDto.getMaxPrice()));
        var priceNull = new BigDecimal("0");
        var address = systemTenderDto.getAddress();

        systemTenderDto.setMinPrice(minPrice.compareTo(priceNull) < 0 ? priceNull : minPrice);
        systemTenderDto.setMaxPrice(maxPrice.compareTo(priceNull) < 0 ? priceNull : maxPrice);
        systemTenderDto.setAddress(address == null ? "" : address);

        var count = tenderRepository.findAllByCustomerId(customer.getUserId()).stream()
                .filter(t -> !Objects.equals(t.getStatus(), Status.DELETED))
                .map(Tender::getTitle)
                .filter(s -> s.equals(systemTenderDto.getTitle()))
                .count();
        if (count > 0) {
            return new ResponseEntity<>(new ResponseError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Tender with this title is in your list tenders"
            ), HttpStatus.BAD_REQUEST);
        }

        var description = String.format(MsgTenders.msgSaveTender.getValue(),
                tender.getTitle());
        sendMessage(adminId, tender.getCustomerId(), tender.getTenderId(), description);

        log.info("Tender saved and return tenderId");
        return new ResponseEntity<>(tender.getTenderId(), HttpStatus.CREATED);

    }

    private Tender createTender(SystemTenderDto systemTenderDto, UUID userId) {
        var tender = Tender.builder()
                .title(systemTenderDto.getTitle())
                .dateStart(systemTenderDto.getDateStart())
                .dateFinish(systemTenderDto.getDateFinish())
                .address(systemTenderDto.getAddress())
                .description(systemTenderDto.getDescription())
                .customerId(userId)
                .minPrice(systemTenderDto.getMinPrice())
                .maxPrice(systemTenderDto.getMaxPrice())
                .status(Status.DRAFT)
                .specializationId(systemTenderDto.getSpecializationId())
                .build();
        return tenderRepository.save(tender);
    }

    @Override
    public ResponseEntity<?> announceTender(Long id, UUID userId) {
        var user = userClient.getUserById(userId);
        var tender = findTenderById(id);

        if (!tender.getCustomerId().equals(user.getUserId())) {
            throw new ResourceNotFoundException("You can't change status, because you not founder this tender");
        }

        tender.setStatus(Status.ANNOUNCED);
        saveOrUpdate(tender);

        var description = String.format(MsgTenders.msgPublicTender.getValue(),
                user.getUserName(),
                tender.getTitle());
        sendMessage(adminId, tender.getCustomerId(), tender.getTenderId(), description);
        log.info("Tender announced and return HttpStatus OK");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id, UUID userId) {
        var user = userClient.getUserById(userId);
        var tender = findTenderById(id);
        if (!userId.equals(tender.getCustomerId())) {
            throw new ResourceNotFoundException("You is not the creator this tender");
        }
        changeStatusTender(id, Status.DELETED);

        var description = String.format(MsgTenders.msgDeleteTender.getValue(),
                user.getUserName(),
                tender.getTitle()
        );
        sendMessage(adminId, tender.getCustomerId(), tender.getTenderId(), description);
//        log.info("Tender deleted and return HttpStatus OK");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> closeTender(Long id) {
        var tender = changeStatusTender(id, Status.COMPLETED);
        sendMessage(adminId, tender.getCustomerId(), tender.getTenderId(), MsgTenders.msgCloseTender.getValue());
//        log.info("Tender closed and return HttpStatus OK");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Tender saveOrUpdate(Tender tender) {
        return tenderRepository.save(tender);
    }

    @Override
    @Transactional
    public ResponseEntity<?> setContractor(ContractorDto contractorDto) {
        var tender = findTenderById(contractorDto.getTenderId());
        var customer = userClient.getUserById(contractorDto.getCustomerId());

        if (!tender.getCustomerId().equals(customer.getUserId())) {
            return new ResponseEntity<>(new ResponseError(HttpStatus.METHOD_NOT_ALLOWED.value(), "Sorry, this is not your tender"), HttpStatus.METHOD_NOT_ALLOWED);
        }

        var contractorId = tender.getResponds().stream()
                .map(Respond::getClientId)
                .filter(r -> r.equals(contractorDto.getContractorId()))
                .findFirst().orElseThrow(() ->
                        new ResourceNotFoundException("User with this id isn't in list 'responds', verify id users"));

        var contractor = userClient.getUserById(contractorId);

        var respondId = tender.getResponds()
                .stream()
                .filter(Objects::nonNull)
                .map(Respond::getId)
                .findFirst().orElseThrow(() ->
                        new ResourceNotFoundException("Respond with this id isn't in list 'responds', verify id " +
                                "users"));


        var respond = respondService.findById(respondId);
        respond.setStatus(Status.ACCEPTED);
        respondService.save(respond);

        tender.setStatus(Status.COMPLETED);
        tender.setContractorId(contractor.getUserId());
        saveOrUpdate(tender);

        var messageForContractorTender = String.format(MsgTenders.msgForContractor.getValue(),
                contractor.getUserName(),
                customer.getUserName());


        var messageForCustomerTender = String.format(MsgTenders.msgForCustomer.getValue(),
                customer.getUserName(),
                contractor.getUserName());
        sendMessage(adminId, tender.getCustomerId(), tender.getTenderId(), messageForCustomerTender);
        sendMessage(tender.getCustomerId(), tender.getContractorId(), tender.getTenderId(), messageForContractorTender);

        var messageForRejectContractorTender = String.format(MsgTenders.msgForRejectContractor.getValue(),
                contractor.getUserName(), tender.getTitle());


        List<Respond> responds = tender.getResponds();
        responds.remove(respond);
        for (Respond res : responds) {
            res.setStatus(Status.REJECTED);
            respondService.save(respond);
            sendMessage(adminId, res.getClientId(), tender.getTenderId(), messageForRejectContractorTender);
        }
        log.info("Tender set contractor and return HttpStatus OK");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> rejectContractor(ContractorDto contractorDto) {
        var tender = findTenderById(contractorDto.getTenderId());
        var respond = tender.getResponds()
                .stream()
                .filter(Objects::nonNull)
                .filter(res -> res.getClientId().equals(contractorDto.getContractorId()))
                .findFirst().orElseThrow(() ->
                        new ResourceNotFoundException("Respond with this id isn't in list 'responds', verify id " +
                                "users"));
        respond.setStatus(Status.REJECTED);
        respondService.save(respond);
        changeStatusTender(contractorDto.getTenderId(), Status.ANNOUNCED);

        var contractor = userClient.getUserById(contractorDto.getContractorId());

        var messageForRejectContractorTender = String.format(MsgTenders.msgForRejectContractor.getValue(),
                contractor.getUserName(), tender.getTitle());

        sendMessage(adminId, contractor.getUserId(), tender.getTenderId(), messageForRejectContractorTender);
        log.info("Tender reject Contractor and return HttpStatus OK");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> addRespond(SystemRespondDto systemRespondDto) {

        var tender = findTenderById(systemRespondDto.getTenderId());
        var user = userClient.getUserById(systemRespondDto.getContractorId());

        if (tender.getCustomerId().equals(user.getUserId())) {
            return new ResponseEntity<>(new ResponseError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                    "Sorry, You cannot respond to your tender"), HttpStatus.METHOD_NOT_ALLOWED);
        }

        var respond = respondService.add(tender, user.getUserId(), systemRespondDto);
        List<Respond> responds;
        if (tender.getResponds().isEmpty()) {
            responds = new ArrayList<>();
        } else {
            responds = tender.getResponds();
        }
        responds.add(respond);
        saveOrUpdate(tender);

        var description = String.format(MsgTenders.msgRespondTender.getValue(),
                tender.getTitle(),
                user.getUserName(),
                systemRespondDto.getTerm(),
                systemRespondDto.getPrice(),
                systemRespondDto.getDescription()
        );

        sendMessage(user.getUserId(), tender.getCustomerId(), tender.getTenderId(), description);
        log.info("Respond add list tender responds and return HttpStatus Created");
        return new ResponseEntity<>(respondMapper.toDto(respond), HttpStatus.CREATED);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshStatusTender() {
        List<Tender> tenderList = tenderRepository.findAllByStatus(Status.ANNOUNCED);
        tenderList.forEach(this::setStatusToDeleted);
    }

    private Tender changeStatusTender(Long tenderId, Status status) {
        var tender = findTenderById(tenderId);
        tender.setStatus(status);
        tender.setAnnounceDate(LocalDateTime.now());
        return saveOrUpdate(tender);

    }

    private void sendMessage(UUID senderId, UUID recipientId, Long tenderId, String description) {
        var message = NewMessage
                .builder()
                .senderId(senderId)
                .recipientId(recipientId)
                .tenderId(tenderId)
                .description(description)
                .build();
        chatClient.send(message);
        log.info("Send message: " + message);
    }

    private Tender findTenderById(Long id) {
        return tenderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Tender with id: " + id));
    }

    private void setStatusToDeleted(Tender tender) {
        if (tender.getAnnounceDate() != null && ChronoUnit.DAYS.between(
                tender.getAnnounceDate(),
                LocalDateTime.now()
        ) > lifeTimeTender) {
            tender.setStatus(Status.DELETED);
            tenderRepository.save(tender);
            if (tender.getCustomerId() != null) {
                UserDto customer = userClient.getUserById(tender.getCustomerId());
                var message = String.format("%s, The tender is deleted!", customer.getUserName());
                sendMessage(adminId, tender.getCustomerId(), tender.getTenderId(), message);
            }
            if (tender.getContractorId() != null) {
                UserDto contractor = userClient.getUserById(tender.getContractorId());
                var message = String.format("%s, The tender is completed!", contractor.getUserName());
                sendMessage(adminId, tender.getContractorId(), tender.getTenderId(), message);
            }
        }
        log.info("Tender status " + tender.getTitle() + " was changed on \"DELETED\"");
    }
}
