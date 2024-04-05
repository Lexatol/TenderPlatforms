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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lexp00.platform.tpcommon.dtos.tenders.LightTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.SystemTenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderDto;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderRequest;
import ru.lexp00.platform.tpcommon.dtos.users.ContractorDto;
import ru.lexp00.platform.tptenders.services.tenders.TendersService;
import ru.lexp00.platform.tptenders.utils.TenderFilters;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tenders")
@Tag(
        name = "Заказы",
        description = "Методы работы с заказами"
)
public class TenderController {
    private final static String FIND_TENDER_BY_ID = "/id/{id}";
    private final static String FIND_TENDER_BY_USER_ID = "/user/{userId}";
    private final static String ADD_TENDER = "/add";
    private final static String SET_CONTRACTOR_TENDER = "/set_contractor";
    private final static String DELETE_TENDER_BY_ID = "/del/{tenderId}";
    private final static String CLOSE_TENDER_BY_ID = "/close_tender/{id}";
    private final static String ANNOUNCE_TENDER = "/announce/{id}";
    private final static String REJECT_CONTRACTOR = "/rejectContractor";
    private final static String FIND_ALL_TENDERS_BY_USER_ID_WHERE_RESPOND_IS = "/responds/user/{userId}";

    private final TendersService tendersService;

    @GetMapping(FIND_TENDER_BY_ID)
    @Operation(
            summary = "Информация о заказе по его id",
            description = "Информация о заказе по его id"
    )
    @ApiResponse(
            content = @Content(
                    schema = @Schema(implementation = TenderDto.class)
            )
    )
    public TenderDto findById(
            @Parameter(description = "Уникальный идентификатор заказа")
            @PathVariable Long id) {
        return tendersService.findById(id);
    }

    @GetMapping(FIND_TENDER_BY_USER_ID)
    @Operation(
            summary = "Метод возвращает список заказов по id пользователя",
            description = "Метод возвращает список заказов по id пользователя"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = LightTenderDto.class)
                    )
            )
    )
    public List<LightTenderDto> findByUserId(
            @Parameter(description = "id пользователя")
            @PathVariable UUID userId) {
        return tendersService.findByUserId(userId);
    }

    @GetMapping(FIND_ALL_TENDERS_BY_USER_ID_WHERE_RESPOND_IS)
    @Operation(
            summary = "Метод возвращает список заказов по id исполнителя, в которых он откликался",
            description = "Метод возвращает список заказов по id исполнителя, в которых он откликался"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = LightTenderDto.class)
                    )
            )
    )
    public List<LightTenderDto> findAllTenderByUserWhereRespondIs(
            @Parameter(description = "id user")
            @PathVariable UUID userId) {
        return tendersService.findAllTendersByUserIdWhereRespondIs(userId);
    }


    @GetMapping
    @Operation(
            summary = "Метод возвращает страницы с заказами",
            description = "Метод возвращает страницы с заказами"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = LightTenderDto.class)
                    )
            )
    )
    public Page<LightTenderDto> findAllTender(
            @Parameter(name = "page_number", description = "Номер страницы", example = "1")
            @RequestParam(defaultValue = "1", name = "page_number") Integer pageNumber,
            @Parameter(name = "page_size", description = "Количество заказов на странице", example = "10")
            @RequestParam(defaultValue = "5", name = "page_size") Integer pageSize,
            @RequestParam(name = "max_price", required = false) @Parameter(description = "Максимальная цена",
                    example = "100000") BigDecimal maxPrice,
            @RequestParam(name = "min_price", required = false) @Parameter(description = "Минимальная цена", example
                    = "100000") BigDecimal minPrice,
            @RequestParam(name = "specialization_id", required = false) @Parameter(description = "Название " +
                    "категории специализации") Long specializationId) {
        TenderRequest request = TenderRequest.builder()
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .specializationId(specializationId)
                .build();
//todo убрать пагинацию или переделать по человечески
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = 5;
        }

        return tendersService.findAll(new TenderFilters(request).getSpecification(), pageNumber - 1, pageSize);
    }

    @PutMapping(ADD_TENDER)
    @Operation(
            summary = "Метод сохранения заказа",
            description = "Метод сохранения заказа"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заказ создан"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Один из параметров заполнения заказа указан не правильно"
                    )
            }
    )
    public ResponseEntity<?> save(@RequestBody SystemTenderDto systemTenderDto) {
        return tendersService.save(systemTenderDto);
    }

    @PostMapping(ANNOUNCE_TENDER)
    @Operation(
            summary = "Метода опубликовать заказ",
            description = "переводит тендер в статус ОПУБЛИКОВАН"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заказ опубликован"
                    )
            }
    )
    public ResponseEntity<?> announce(
            @Parameter(description = "id заказа")
            @PathVariable Long tenderId,
            @Parameter(description = "id пользователя")
            @PathVariable UUID userId) {
        return tendersService.announceTender(tenderId, userId);
    }


    @DeleteMapping(DELETE_TENDER_BY_ID)
    @Operation(
            summary = "Метод удаления заказа",
            description = "Метод присваивает заказу статус УДАЛЕН"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "статус тендера «УДАЛЕН»"
                    )
            }
    )
    public ResponseEntity<?> delete(
            @Parameter(description = "id заказ")
            @PathVariable Long tenderId,
            @Parameter(description = "id пользователя")
            @PathVariable UUID userId) {
        return tendersService.delete(tenderId, userId);
    }



    @PostMapping(REJECT_CONTRACTOR)
    @Operation(
            summary = "Метод отклонения заказчиком предложения исполнителя",
            description = "Метод отклонения заказчиком предложения исполнителя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "отклик на заказ отклонен"
                    )
            }
    )
    public ResponseEntity<?> rejectContractor(@RequestBody ContractorDto contractorDto) {
        return tendersService.rejectContractor(contractorDto);
    }

    @PostMapping(SET_CONTRACTOR_TENDER)
    @Operation(
            summary = "Метод для выбор исполнителя заказчиком",
            description = "Метод для выбор исполнителя заказчиком"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заказчик выбрал исполнителя своего заказа и отправил ему свое согласие и контакты"
                    )
            }
    )
    public ResponseEntity<?> setContractor(@RequestBody ContractorDto contractorTender) {
        return tendersService.setContractor(contractorTender);
    }


    @GetMapping(CLOSE_TENDER_BY_ID)
    @Operation(
            summary = "Метод завершения заказа, закрытия",
            description = "Метод завершения заказа, закрытия"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заказ закрыт"
                    )
            }
    )
    public ResponseEntity<?> closeTender(
            @Parameter(description = "id заказа")
            @PathVariable Long id) {
        return tendersService.closeTender(id);
    }

}
