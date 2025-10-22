package com.spring.controle_remessas_api.domain.services;

import com.spring.controle_remessas_api.domain.entities.CityEntity;
import com.spring.controle_remessas_api.domain.repositories.CityRepository;
import com.spring.controle_remessas_api.web.dto.input.CityRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;


@Service
public class CityService {

    private final CityRepository cityRepository;


    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional
    public CityEntity createCity(CityRequestDto cityDto){
        String nameTrimmed = cityDto.name().trim();

        var existing = cityRepository.findByNameIgnoreCase(nameTrimmed);

        if (existing.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cidade já existe no banco de dados");
        }

        CityEntity newCity = new CityEntity();
        newCity.setName(nameTrimmed);
        return cityRepository.save(newCity);
    }

}
