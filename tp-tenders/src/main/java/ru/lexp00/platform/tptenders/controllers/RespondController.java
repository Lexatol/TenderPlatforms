package ru.lexp00.platform.tptenders.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lexp00.platform.tpcommon.dtos.responds.RespondDto;
import ru.lexp00.platform.tpcommon.dtos.responds.SystemRespondDto;
import ru.lexp00.platform.tptenders.services.responds.RespondService;
import ru.lexp00.platform.tptenders.services.tenders.TendersService;

import java.util.List;

@RestController
@RequestMapping("api/v1/responds")
@Tag(
        name = "Отклики на заказы",
        description = "Методы работы с откликами на заказами"
)
@RequiredArgsConstructor
public class RespondController {
    private final static String FIND_ALL_RESPONDS_BY_TENDER_ID = "/responds/{tenderId}";
    private final static String ADD_RESPOND_TENDER = "/add_respond";

    private final RespondService respondService;
    private final TendersService tendersService;



    @Operation(
            summary = "Метод возвращает отклики на заказ по ид заказа",
            description = "Метод возвращает отклики на заказ по ид заказа"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = RespondDto.class)
                    )
            )
    )
    @GetMapping(FIND_ALL_RESPONDS_BY_TENDER_ID)
    public List<RespondDto> findAllResponds(
            @Parameter(description = "Ид заказа")
            @PathVariable Long tenderId) {
        return respondService.findAllRespondByTenderId(tenderId);
    }

    @PostMapping(ADD_RESPOND_TENDER)
    @Operation(
            summary = "Метод добавления отклика на заказ",
            description = "Исполнитель откликается на заказ"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отклик на тендер отправлен"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователя с таким id нет в нашей базе"
                    ),
                    @ApiResponse(
                            responseCode = "405",
                            description = "Вы уже откликались на этот тендер"
                    )
            }
    )
    public ResponseEntity<?> addRespond(@RequestBody SystemRespondDto systemRespondDto) {
        return tendersService.addRespond(systemRespondDto);
    }
}
