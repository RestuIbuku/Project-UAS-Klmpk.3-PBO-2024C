package com.example.ProjectUASKlmpk3PBO2024C.repository;

import com.example.ProjectUASKlmpk3PBO2024C.entity.ParkiranEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParkiranRepository extends JpaRepository<ParkiranEntity, Long> {
    List<ParkiranEntity> findByWaktuKeluarBetweenAndStatusBayarTrue(LocalDateTime start, LocalDateTime end);
    
    Long countByStatusBayarTrue();
}
