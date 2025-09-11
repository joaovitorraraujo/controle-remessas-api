package com.spring.controle_remessas_api.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRegisterDTO{
    private String userName;
    private String cpf;
    private String password;
    private String  role;
}
