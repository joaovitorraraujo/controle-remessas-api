package com.spring.controle_remessas_api.security.auth.dto;

import com.spring.controle_remessas_api.domain.enums.RoleEnum;

public record RegisterRequestDTO (String userName, String cpf, String password, RoleEnum  roleEnum){
}
