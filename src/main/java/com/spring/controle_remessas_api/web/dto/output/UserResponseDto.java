package com.spring.controle_remessas_api.web.dto.output;

import com.spring.controle_remessas_api.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;

    public UserResponseDto(UserEntity userEntity){
        this.name = userEntity.getUsername();
    }
}
