    package com.spring.controle_remessas_api.web.controllers.auth;

    import com.spring.controle_remessas_api.domain.entities.UserEntity;
    import com.spring.controle_remessas_api.domain.services.UserService;
    import com.spring.controle_remessas_api.security.auth.dto.LoginRequestDTO;
    import com.spring.controle_remessas_api.security.auth.dto.LoginResponseDTO;
    import com.spring.controle_remessas_api.security.auth.dto.RegisterRequestDTO;
    import com.spring.controle_remessas_api.security.auth.dto.TestRegisterDTO;
    import com.spring.controle_remessas_api.security.auth.service.AuthenticationService;
    import com.spring.controle_remessas_api.web.dto.UserDTO;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

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

        @GetMapping("public")
        public String publicGet(){
            return "acesso ao publicGet";
        }

        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody String userName){
            return ResponseEntity.ok(userName);
        }

        @PostMapping("/test")
        public ResponseEntity<TestRegisterDTO> registerTest(@RequestBody TestRegisterDTO requestDTO) {
//            System.out.println(">>> DTO recebido: " + requestDTO.toString());
//            String response = requestDTO.getUserName();
            return ResponseEntity.ok(requestDTO);
        }


        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> login(){
            return null;
        }

    //    @PostMapping("/register")
    //    public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterRequestDTO requestDTO){
    //        System.out.println(">>> CHEGOU NO REGISTER <<<");
    //        UserEntity newUser = userService.registerUser(requestDTO);
    //
    //        UsernamePasswordAuthenticationToken authToken =
    //                new UsernamePasswordAuthenticationToken(
    //                        newUser.getCpf(),
    //                        null,
    //                        newUser.getRole() != null ?
    //                                List.of(newUser.getRole()) : List.of()
    //                );
    //
    //        String token = authenticationService.authenticate(authToken);
    //
    //        return ResponseEntity.ok(new LoginResponseDTO(token));
    //    }


    }
