package com.spring.controle_remessas_api.web.controllers.auth;

import com.spring.controle_remessas_api.security.auth.dto.LoginRequestDTO;
import com.spring.controle_remessas_api.security.auth.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationService authenticationService,
                                    AuthenticationManager authenticationManager) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("public")
    public String publicGet(){
        return "acesso ao publicGet";
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.cpf(),
                        loginRequest.passsword()
                )
        );

        return authenticationService.authenticate(authentication);
    }
}
