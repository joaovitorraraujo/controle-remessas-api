package com.spring.controle_remessas_api.web.controllers.privateControllers;


import com.spring.controle_remessas_api.config.SecurityConfig;
import com.spring.controle_remessas_api.domain.services.UserService;

import com.spring.controle_remessas_api.web.dto.output.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Controller para manipular os dados relacionados aos usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Lista todos usuarios", description = "Metodo para listar todos os usuarios do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "usuarios do sistema"),
            @ApiResponse(responseCode = "400", description = "sem autorização")
    }
    )
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
