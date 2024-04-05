package ru.lexp00.platform.tptenders.services;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lexp00.platform.tpcommon.dtos.specializations.SpecDto;

@FeignClient("tp-specialization")
public interface SpecializationClient {

    @GetMapping("api/v1/spec/{id}")
    public SpecDto findById(
            @Parameter(description = "id специализации")
            @PathVariable Long id);
}
