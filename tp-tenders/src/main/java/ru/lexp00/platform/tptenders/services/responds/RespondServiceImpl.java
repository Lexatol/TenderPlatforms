package ru.lexp00.platform.tptenders.services.responds;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.lexp00.platform.tpcommon.dtos.responds.RespondDto;
import ru.lexp00.platform.tpcommon.dtos.responds.SystemRespondDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpcommon.exceptions.ResponseError;
import ru.lexp00.platform.tptenders.entities.Respond;
import ru.lexp00.platform.tptenders.entities.Tender;
import ru.lexp00.platform.tptenders.enums.Status;
import ru.lexp00.platform.tptenders.mappers.RespondMapper;
import ru.lexp00.platform.tptenders.repositories.RespondRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespondServiceImpl implements RespondService {
    private final RespondRepository respondRepository;
    private final RespondMapper respondMapper;

    @Override
    public Respond findById(Long id) {
        return respondRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found respond with id: " + id));
    }

    @Override
    public List<Respond> findByUserId(UUID userId) {
        return respondRepository.findByClientId(userId).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Respond save(Respond respond) {
        return respondRepository.save(respond);
    }

    @Override
    public List<RespondDto> findAllRespondByTenderId(Long tenderId) {
        List<Respond> responds = respondRepository.findAllRespondByTenderId(tenderId);
        return responds.stream()
                .map(respondMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Respond add(Tender tender, UUID userId, SystemRespondDto respond) {
        var count = tender.getResponds().stream()
                .filter(r -> r.getClientId().equals(userId))
                .count();
        if (count > 0) {
            new ResponseError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                    "Sorry, You send respond at this tender");
        }
        return save(Respond.builder()
                .tender(tender)
                .clientId(userId)
                .description(respond.getDescription())
                .price(respond.getPrice())
                .term(respond.getTerm())
                .status(Status.SENT)
                .build());
    }
}
