package com.spring.controle_remessas_api.domain.services;

import com.spring.controle_remessas_api.domain.entities.UserEntity;
import com.spring.controle_remessas_api.domain.repositories.UserRepository;
import com.spring.controle_remessas_api.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> gettAllUsers(){
        return userRepository.findAll();
    }
}
