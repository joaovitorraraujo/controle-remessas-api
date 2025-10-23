package com.spring.controle_remessas_api.config;

import com.spring.controle_remessas_api.domain.entities.UserEntity;
import com.spring.controle_remessas_api.domain.enums.RoleEnum;
import com.spring.controle_remessas_api.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.user-cpf}")
    private String adminCpf;

    @Value("${admin.password}")
    private String adminPassword;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserConfig(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findByUsername(adminUsername);

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new UserEntity();
                    user.setUsername(adminUsername);
                    user.setCpf(adminCpf);
                    user.setPassword(passwordEncoder.encode(adminPassword));
                    user.setRole(RoleEnum.ROLE_ADMIN);
                    userRepository.save(user);
                }
        );
    }
}