package ru.lexp00.platform.tpspecialization.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lexp00.platform.tpcommon.dtos.specializations.SpecDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpspecialization.entities.Specialization;
import ru.lexp00.platform.tpspecialization.repositories.SpecializationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecializationServices {
    private final SpecializationRepository specializationRepository;

    public SpecDto findSpecById(Long id) {
        Specialization sp = specializationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization with id: " + id));
        return toDto(sp);
    }

    public Specialization findById(Long id) {
        return specializationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization with id: " + id));
    }

    public List<SpecDto> findAllSpecDto() {
        return specializationRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    public Specialization findByTitle(String title) {
        return specializationRepository.findByTitle(title).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find specialization with title: " + title));
    }

    public SpecDto toDto(Specialization sp) {
        return SpecDto.builder()
                .id(sp.getId())
                .title(sp.getTitle())
                .build();
    }
}
