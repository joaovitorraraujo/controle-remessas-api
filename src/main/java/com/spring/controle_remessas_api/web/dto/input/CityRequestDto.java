package com.spring.controle_remessas_api.web.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CityRequestDto(
        @NotNull
        @NotEmpty
        String name
) {
}
