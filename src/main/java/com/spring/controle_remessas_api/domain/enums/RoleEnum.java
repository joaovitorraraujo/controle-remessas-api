package com.spring.controle_remessas_api.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_RESPONSIBLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
