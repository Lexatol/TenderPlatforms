package ru.lexp00.platform.tpspecialization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lexp00.platform.tpspecialization.entities.SpecCategory;

import java.util.Optional;

@Repository
public interface SpecCategoryRepository extends JpaRepository<SpecCategory, Long> {

    Optional<SpecCategory> findByTitle(String title);
}
