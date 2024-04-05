package ru.grow2up.tpcore.dtos.tenders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenderDto {

    @Schema(
            description = "id заказа",
            example = "1",
            hidden = true
    )
    private Long tenderId;

    @Schema(
            description = "Название заказа",
            example = "Косметический ремонт квартиры",
            required = true
    )
    private String title;

    @Schema(
            description = "Дата, когда необходимо приступить к выполнению заказа 'c ..'",
            example = "12 09 2022"
    )
    private String dateStart;

    @Schema(
            description = "Дата, когда необходимо приступить к выполнению заказа 'до'",
            example = "12 09 2022"
    )
    private String dateFinish;

    @Schema(
            description = "Адрес нахождения объекта",
            example = "Нижний сыромятнический пер д 1",
            required = true
    )
    private String address;

    @Schema(
            description = "Имя пользователя, который выиграл заказ",
            example = "Вася Петров"
    )
    private String contractor;

    @Schema(
            description = "Имя пользователя, который создал заказ",
            example = "Сергей Михайлович",
            required = true
    )
    private String customer;

    @Schema(
            description = "ID пользователя, который создал заказ",
            example = "",
            required = true
    )
    private UUID customerId;

    @Schema(
            description = "Описание заказа",
            example = "Выполнить косметический ремонт квартиры 350м2"
    )
    private String description;

    @Schema(
            description = "Предположительный бюджет",
            example = "10000"
    )
    private BigDecimal minPrice;

    @Schema(
            description = "Предположительный бюджет",
            example = "2500000"
    )
    private BigDecimal maxPrice;

    @Schema(
            description = "('DRAFT'), ('ANNOUNCED'), ('WAIT_FOR_CONFIRMATION'), ('CONFIRMED'), ('COMPLETED'), ('DELETED')"
    )
    private String status;

    @Schema(
            description = "Название категории специализации, в которой размещен заказ",
            example = "Косметический ремонт квартир",
            required = true
    )
    private String specializationTitle;

    @Schema(
            description = "Идентификатор специализации в которой размещен заказ",
            example = "1",
            required = true
    )
    private Long specializationId;

    @Schema(
            description = "Название страны места нахождения объекта",
            example = "Россия",
            required = true
    )
    private String countryTitle;

    @Schema(
            description = "Идентификатор страны места нахождения объекта",
            example = "1",
            required = true
    )
    private Long countryId;

    @Schema(
            description = "Название региона места нахождения объекта",
            example = "Московская область",
            required = true
    )
    private String regionTitle;

    @Schema(
            description = "Идентификатор региона места нахождения объекта",
            example = "1",
            required = true
    )
    private Long regionId;

    @Schema(
            description = "Название города места нахождения объекта",
            example = "Люберцы",
            required = true
    )
    private String cityTitle;

    @Schema(
            description = "Идентификатор города места нахождения объекта",
            example = "Люберцы",
            required = true
    )
    private Long cityId;

//    @Schema(
//            description = "Список фото"
//    )
//    private List<Photo> photos;

    @Schema(
            description = "Дата создания заказа"
    )
    private LocalDateTime createAt;

    @Schema(
            description = "Дата публикации заказа"
    )
    private LocalDateTime announceDate;

    @Schema(
            description = "Количество откликов на заказ"
    )
    private Integer countResponds;

    @Schema(
            description = "Флаг с проживанием на объекте",
            example = "false"
    )
    private Boolean withAccommodation;

    @Schema(
            description = "Флаг с материалами исполнителя"
    )
    private boolean contractorsMaterials;
}
