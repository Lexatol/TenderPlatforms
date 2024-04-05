package ru.lexp00.platform.tptenders.utils;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.lexp00.platform.tpcommon.dtos.tenders.TenderRequest;
import ru.lexp00.platform.tptenders.entities.Tender;


@Getter
public class TenderFilters {


    private Specification<Tender> specification;

    public TenderFilters(TenderRequest request) {
        specification = Specification.where(null);
        if (request.getSpecializationId() != null) {
            specification = specification.and(TenderSpecifications.specializationTitleLike(request.getSpecializationId()));
        }
        if (request.getMinPrice() != null) {
            specification = specification.and(TenderSpecifications.priceGreaterOrEqualsThan(request.getMinPrice()));
        }
        if (request.getMaxPrice() != null) {
            specification = specification.and(TenderSpecifications.priceLesserOrEqualsThan(request.getMaxPrice()));
        }
    }
}
