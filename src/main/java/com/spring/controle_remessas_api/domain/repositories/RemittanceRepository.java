package com.spring.controle_remessas_api.domain.repositories;

import com.spring.controle_remessas_api.domain.entities.RemittanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RemittanceRepository extends JpaRepository<RemittanceEntity, UUID> {
}
