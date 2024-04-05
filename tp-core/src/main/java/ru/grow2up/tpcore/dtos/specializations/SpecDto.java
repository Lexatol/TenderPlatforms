package ru.grow2up.tpcore.dtos.specializations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecDto {

    @Schema(
            description = "id специализации",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Название специализации",
            example = "Строительство монолитного фундамента"
    )
    private String title;
}
