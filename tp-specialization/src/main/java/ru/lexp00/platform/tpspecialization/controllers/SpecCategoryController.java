package ru.lexp00.platform.tpspecialization.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lexp00.platform.tpcommon.dtos.specializations.SpecCategoryDto;
import ru.lexp00.platform.tpspecialization.services.SpecCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/specCategory")
@Tag(
        name = "Категории специализаций",
        description = "Методы для работы с категориями специализаций"
)
public class SpecCategoryController {
    private final SpecCategoryService specCategoryService;

    @GetMapping
    @Operation(
            summary = "Метод возвращает список категорий специализаций",
            description = "Метод возвращает список категорий специализаций"
    )
    @ApiResponse(
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(implementation = SpecCategoryDto.class)
                    )
            )
    )
    public List<SpecCategoryDto> getAll() {
        return specCategoryService.findAll();
    }
}
