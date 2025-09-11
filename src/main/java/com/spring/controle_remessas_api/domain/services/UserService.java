package com.spring.controle_remessas_api.domain.services;

import com.spring.controle_remessas_api.domain.entities.UserEntity;
import com.spring.controle_remessas_api.domain.repositories.UserRepository;

import com.spring.controle_remessas_api.security.auth.dto.RegisterRequestDTO;
import com.spring.controle_remessas_api.web.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity  registerUser(RegisterRequestDTO requestDTO){
        Optional<UserEntity> user = userRepository.findByCpf(requestDTO.cpf());
        if (user.isPresent()){
            throw new RuntimeException("Usuário já existe");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(requestDTO.userName());
        newUser.setCpf(requestDTO.cpf());
        newUser.setRole(requestDTO.roleEnum());
        newUser.setPassword(passwordEncoder.encode(requestDTO.password()));


      return userRepository.save(newUser);
    }
}
