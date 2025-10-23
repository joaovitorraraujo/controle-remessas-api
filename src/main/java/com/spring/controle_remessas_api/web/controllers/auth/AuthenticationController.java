    package com.spring.controle_remessas_api.web.controllers.auth;

    import com.spring.controle_remessas_api.config.SecurityConfig;
    import com.spring.controle_remessas_api.domain.entities.UserEntity;
    import com.spring.controle_remessas_api.domain.services.UserService;
    import com.spring.controle_remessas_api.security.auth.dto.LoginRequestDTO;
    import com.spring.controle_remessas_api.security.auth.dto.LoginResponseDTO;
    import com.spring.controle_remessas_api.security.auth.dto.RegisterRequestDTO;
    import com.spring.controle_remessas_api.security.auth.service.AuthenticationService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    @Tag(name = "Auth", description = "Controller para manipular o registro e login de usuário.")
    @SecurityRequirement(name = SecurityConfig.SECURITY)
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
        @Operation(summary = "Loga um usuario existente", description = "Metodo para logar um usuário no sistema.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Usuário logado com sucesso, retornando o Token."),
                @ApiResponse(responseCode = "401", description = "Usuário não encontrado no banco.")
        }
        )
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
        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        @Operation(summary = "Cria um usuario", description = "Metodo criar um usuário no sistema. Apenas um usuário com a role ADMIN pode criar mais usuários." +
                "Para testes utilize o usuário admin padrao criado automaticamente pelo sistema moficando seus determinados valores no .env conforme o .env.example")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso, retornando o Token."),
                @ApiResponse(responseCode = "401", description = "Usuário já encontrado no banco ou sem autorização para crair usuarios.")
        }
        )
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
