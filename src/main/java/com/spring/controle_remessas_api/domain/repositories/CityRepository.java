package com.spring.controle_remessas_api.domain.repositories;

import com.spring.controle_remessas_api.domain.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, UUID> {

    Optional<CityEntity> findByNameIgnoreCase(String name);
}
