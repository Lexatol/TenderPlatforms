package ru.lexp00.platform.tptenders.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.lexp00.platform.tptenders.entities.Tender;

import java.math.BigDecimal;

public class TenderSpecifications {

    public static Specification<Tender> priceGreaterOrEqualsThan(BigDecimal minPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("minPrice"), minPrice);
    }

    public static Specification<Tender> priceLesserOrEqualsThan(BigDecimal maxPrice) {
        return  (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("maxPrice"), maxPrice);
    }

    public static Specification<Tender> specializationTitleLike(Long specializationId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("specialization").get("id"), specializationId
        );
    }
}
