package ru.lexp00.platform.tptenders.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lexp00.platform.tpcommon.dtos.responds.RespondDto;
import ru.lexp00.platform.tptenders.entities.Respond;
import ru.lexp00.platform.tptenders.services.UserClient;

@Component
@RequiredArgsConstructor
public class RespondMapperImpl implements RespondMapper {

    private UserClient userClient;

    @Override
    public RespondDto toDto(Respond respond) {

        String clientName = userClient.getUserById(respond.getClientId()).getUserName();

        return RespondDto.builder()
                .id(respond.getId())
                .clientName(clientName)
                .userId(respond.getClientId())
                .description(respond.getDescription())
                .price(respond.getPrice())
                .term(respond.getTerm())
                .status(respond.getStatus().name())
                .build();
    }
}
