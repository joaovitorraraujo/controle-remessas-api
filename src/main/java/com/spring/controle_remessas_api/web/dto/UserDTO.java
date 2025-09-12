package com.spring.controle_remessas_api.web.dto;

import com.spring.controle_remessas_api.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;

    public UserDTO(UserEntity userEntity){
        this.name = userEntity.getUsername();
    }
}
