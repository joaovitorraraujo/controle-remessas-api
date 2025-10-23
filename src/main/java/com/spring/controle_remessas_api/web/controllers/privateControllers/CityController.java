package com.spring.controle_remessas_api.web.controllers.privateControllers;

import com.spring.controle_remessas_api.config.SecurityConfig;
import com.spring.controle_remessas_api.domain.entities.CityEntity;
import com.spring.controle_remessas_api.domain.services.CityService;
import com.spring.controle_remessas_api.web.dto.input.CityRequestDto;
import com.spring.controle_remessas_api.web.dto.output.CityResponseDto;
import com.spring.controle_remessas_api.web.dto.output.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/city")
@Tag(name = "Cidade", description = "Controller para manipular os dados relacionados as cidades")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Cria uma cidade", description = "Metodo para criar uma cidade no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cidade criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Cidade ja existe no banco.")
    }
    )
    public ResponseEntity<CityResponseDto> createCity(@RequestBody @Valid CityRequestDto cityDto){
        CityEntity created = cityService.createCity(cityDto);

        CityResponseDto response = new CityResponseDto(created.getId(),created.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Deleta uma cidade", description = "Deleta uma cidade e todas as suas remessas associadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cidade deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada.")
    })
    public ResponseEntity<Void> deleteCity(@PathVariable UUID id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Lista todas as cidades", description = "Metodo para listar todas as cidades do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cidades do sistema"),
            @ApiResponse(responseCode = "400", description = "sem autorização")
    }
    )
    public ResponseEntity<List<CityResponseDto>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }
}
