package ru.grow2up.tpcore.dtos.responds;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespondDto {

    @Schema(
            description = "id отклика на тендер",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Имя пользователя (название компании) отклика",
            example = "Михаил Сергеевич"
    )
    private String clientName;

    @Schema(
            description = "id пользователя (название компании) отклика",
            example = "21314ce8-c1ac-43fd-bb98-2b6817da146c"
    )
    private UUID userId;

    @Schema(
           description = "Текст, который исполнитель может добавить в отклик",
            example = "Могу выполнить ваш заказ быстро и качественно, но вот дешево не обещаю"
    )
    private String description;

    @Schema(
            description = "Цена, за которую исполнитель готов выполнить заказ",
            example = "10 000"
    )
    private Long price;

    @Schema(
            description = "Сроки выполнения заказа",
            example = "10 месяцев"
    )
    private String term;

    @Schema(
            description =  " 7 - SENT, 8 - ACCEPTED, 9 - REJECTED"
    )
    private String status;
}
