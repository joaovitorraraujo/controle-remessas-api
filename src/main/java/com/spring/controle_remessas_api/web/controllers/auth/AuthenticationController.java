    package com.spring.controle_remessas_api.web.controllers.auth;

    import com.spring.controle_remessas_api.domain.entities.UserEntity;
    import com.spring.controle_remessas_api.domain.services.UserService;
    import com.spring.controle_remessas_api.security.auth.dto.LoginRequestDTO;
    import com.spring.controle_remessas_api.security.auth.dto.LoginResponseDTO;
    import com.spring.controle_remessas_api.security.auth.dto.RegisterRequestDTO;
    import com.spring.controle_remessas_api.security.auth.service.AuthenticationService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthenticationController {

        private final AuthenticationService authenticationService;
        private final AuthenticationManager authenticationManager;
        private final UserService userService;

        public AuthenticationController(AuthenticationService authenticationService,
                                        AuthenticationManager authenticationManager,
                                        UserService userService) {
            this.authenticationService = authenticationService;
            this.authenticationManager = authenticationManager;
            this.userService = userService;
        }


        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.cpf(),
                            requestDTO.password()
                    )
            );

            String token = authenticationService.authenticate(authentication);

            return ResponseEntity.ok(new LoginResponseDTO(token));
        }

        @PostMapping("/register")
        public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterRequestDTO requestDTO){
            UserEntity newUser = userService.registerUser(requestDTO);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.cpf(),
                            requestDTO.password()
                    )
            );

            String token = authenticationService.authenticate(authentication);

            return ResponseEntity.ok(new LoginResponseDTO(token));
        }


    }
