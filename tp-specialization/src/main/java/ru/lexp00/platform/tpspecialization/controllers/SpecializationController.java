package ru.lexp00.platform.tpspecialization.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lexp00.platform.tpcommon.dtos.specializations.SpecDto;
import ru.lexp00.platform.tpspecialization.services.SpecializationServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/spec")
@Tag(
        name = "Специализации",
        description = "Методы для работы со специализациями"
)
public class SpecializationController {
    private final static String FIND_SPECIALIZATION_BY_ID = "/{id}";

    private final SpecializationServices specializationServices;

    @GetMapping
    @Operation(
            summary = "Метод возвращает список специализаций",
            description = "Метод возвращает список специализаций"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = SpecDto.class)
                    )
            )
    )
    public List<SpecDto> findAllSpecDto() {
        return specializationServices.findAllSpecDto();
    }

    @GetMapping(FIND_SPECIALIZATION_BY_ID)
    @Operation(
            summary = "Метод возвращает специализацию по ее id",
            description = "Метод возвращает специализацию по ее id"
    )
    @ApiResponse(
            content = @Content(
                    schema = @Schema(implementation = SpecDto.class)
            )
    )
    public SpecDto findById(
            @Parameter(description = "id специализации")
            @PathVariable Long id) {
        return specializationServices.findSpecById(id);
    }
}