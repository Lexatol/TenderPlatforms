package ru.lexp00.platform.tpspecialization.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lexp00.platform.tpcommon.dtos.specializations.SpecCategoryDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpspecialization.entities.SpecCategory;
import ru.lexp00.platform.tpspecialization.repositories.SpecCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecCategoryService {
    private final SpecCategoryRepository specCategoryRepository;

    private final SpecializationServices specializationServices;

    public List<SpecCategoryDto> findAll() {
        return specCategoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public SpecCategory findByTitle(String title) {
        return specCategoryRepository.findByTitle(title).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization category with title: " + title));
    }

    public SpecCategoryDto toDto(SpecCategory specCategory) {
        return SpecCategoryDto.builder()
                .id(specCategory.getId())
                .title(specCategory.getTitle())
                .specList(specCategory.getSpecList().stream().map(specializationServices::toDto).collect(Collectors.toList()))
                .build();
    }

    public SpecCategory findById(Long specCategoryId) {
        return specCategoryRepository.findById(specCategoryId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization category with id: " + specCategoryId));
    }
}
