package ru.grow2up.tpcore.dtos.responds;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemRespondDto {

    @Schema(
            description = "id исполнителя",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472"
    )
    private UUID contractorId;

    @Schema(
            description = "id заказа, на который производится отклик",
            example = "1",
            required = true
    )
    @NotNull
    private Long tenderId;

    @Schema(
            description = "Текст, который исполнитель может добавить в отклик",
            example = "Могу выполнить ваш заказ быстро и качественно, но вот дешево не обещаю"
    )
    @NotNull
    private String description;

    @Schema(
            description = "Цена, за которую исполнитель готов выполнить заказ",
            example = "10 000"
    )
    @NotNull
    @DecimalMin(value = "0.0", message = "Price must be greater 0")
    @DecimalMax(value = "999999999999.99", message = "Price must me less 999999999999.99")
    private Long price;

    @Schema(
            description = "Сроки выполнения заказа",
            example = "10 месяцев"
    )
    private String term;
}

