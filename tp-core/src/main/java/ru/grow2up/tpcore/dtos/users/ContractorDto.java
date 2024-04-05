package ru.grow2up.tpcore.dtos.users;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDto {

    @Schema(
            description = "id тендера",
            example = "1"
    )
    private Long tenderId;

    @Schema(
            description = "id заказчика",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472"
    )
    private UUID customerId;

    @Schema(
            description = "id исполнителя",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472"
    )
    private UUID contractorId;

}