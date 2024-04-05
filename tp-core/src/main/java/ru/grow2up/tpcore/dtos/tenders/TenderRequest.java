package ru.grow2up.tpcore.dtos.tenders;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenderRequest {
    private Long cityId;
    private Long regionId;
    private Long countryId;
    private Long specializationId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}

