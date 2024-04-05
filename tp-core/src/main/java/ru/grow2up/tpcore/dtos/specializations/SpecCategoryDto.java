package ru.grow2up.tpcore.dtos.specializations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecCategoryDto {


    @Schema(
            description = "id категории специалиазции",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "название категории специализации",
            example = "Комплексные работы"
    )
    private String title;

    @Schema(
            description = "список специализаций"
    )
    private List<SpecDto> specList;
}
