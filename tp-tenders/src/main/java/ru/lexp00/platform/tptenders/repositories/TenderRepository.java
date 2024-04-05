package ru.lexp00.platform.tptenders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.lexp00.platform.tptenders.entities.Tender;
import ru.lexp00.platform.tptenders.enums.Status;

import java.util.List;
import java.util.UUID;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long>, JpaSpecificationExecutor<Tender> {
    List<Tender> findAllByCustomerId(UUID id);
    List<Tender> findAllByStatus(Status status);
}
