package ru.grow2up.tpcore.dtos.tenders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemTenderDto {

    @Schema(
            description = "Уникальные идентификационный номер Пользователя",
            example = "dc4d798b-44e1-4edf-9e8e-8dc8043ab472",
            required = true
    )
    @NotNull
    private UUID userId;

    @Schema(
            description = "Название заказа",
            example = "Косметический ремонт квартиры",
            required = true
    )
    @NotNull
    private String title;

    @Schema(
            description = "Дата, когда необходимо приступить к выполнению заказа 'c ..'",
            example = "12 09 2022"
    )
    @NotNull
    private String dateStart;

    @Schema(
            description = "Дата, когда необходимо приступить к выполнению заказа 'до'",
            example = "12 09 2022"
    )
    @NotNull
    private String dateFinish;

    @Schema(
            description = "Адрес нахождения объекта",
            example = "Нижний сыромятнический пер д 1"
    )
    private String address;

    @Schema(
            description = "Описание заказа",
            example = "Выполнить косметический ремонт квартиры 350м2"
    )
    private String description;

    @Schema(
            description = "Предположительный бюджет",
            example = "10000"
    )
    @NotNull
    @DecimalMin(value = "0.0", message = "Price must be greater 0")
    @DecimalMax(value = "999999999999.99", message = "Price must me less 999999999999.99")
    private BigDecimal minPrice;

    @Schema(
            description = "Предположительный бюджет",
            example = "2500000"
    )
    @NotNull
    @DecimalMin(value = "0.0", message = "Price must be greater 0")
    @DecimalMax(value = "999999999999.99", message = "Price must me less 999999999999.99")
    private BigDecimal maxPrice;

    @Schema(
            description = "id специализации",
            example = "1",
            required = true
    )
    @NotNull
    private Long specializationId;

}
