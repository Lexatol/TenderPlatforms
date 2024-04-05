package ru.lexp00.platform.tptenders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lexp00.platform.tptenders.entities.Respond;

import java.util.List;
import java.util.UUID;

@Repository
public interface RespondRepository extends JpaRepository<Respond, Long> {


    List<Respond> findByClientId(UUID clientId);

    @Query("select r from Respond r where r.tender.tenderId = :tenderId")
    List<Respond> findAllRespondByTenderId(Long tenderId);
}
