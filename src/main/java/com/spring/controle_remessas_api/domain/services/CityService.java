package com.spring.controle_remessas_api.domain.services;

import com.spring.controle_remessas_api.domain.entities.CityEntity;
import com.spring.controle_remessas_api.domain.entities.UserEntity;
import com.spring.controle_remessas_api.domain.repositories.CityRepository;
import com.spring.controle_remessas_api.web.dto.input.CityRequestDto;
import com.spring.controle_remessas_api.web.dto.output.CityResponseDto;
import com.spring.controle_remessas_api.web.dto.output.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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

    @Transactional
    public void deleteCity(UUID id) {
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada."));

        city.getRemittances().clear();

        cityRepository.delete(city);
    }

    public List<CityResponseDto> getAllCities() {
        List<CityEntity> allCities = cityRepository.findAll();

        return allCities.stream()
                .map(city -> new CityResponseDto(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }

}
