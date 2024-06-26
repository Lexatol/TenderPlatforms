package ru.lexp00.platform.tpspecialization.repositories;

import ru.lexp00.platform.tpspecialization.entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {


    Optional<Specialization> findByTitle(String title);
}
