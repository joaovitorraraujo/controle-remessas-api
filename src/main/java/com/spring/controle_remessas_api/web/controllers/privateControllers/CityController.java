package com.spring.controle_remessas_api.web.controllers.privateControllers;

import com.spring.controle_remessas_api.domain.entities.CityEntity;
import com.spring.controle_remessas_api.domain.services.CityService;
import com.spring.controle_remessas_api.web.dto.input.CityRequestDto;
import com.spring.controle_remessas_api.web.dto.output.CityResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CityResponseDto> createCity(@RequestBody @Valid CityRequestDto cityDto){
        CityEntity created = cityService.createCity(cityDto);

        CityResponseDto response = new CityResponseDto(created.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
