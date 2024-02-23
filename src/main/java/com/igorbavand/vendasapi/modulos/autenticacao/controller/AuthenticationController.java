package com.igorbavand.vendasapi.modulos.autenticacao.controller;

import com.igorbavand.vendasapi.modulos.autenticacao.dto.AuthenticationDto;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.RegisterDto;
import com.igorbavand.vendasapi.modulos.autenticacao.dto.TokenResponseDto;
import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import com.igorbavand.vendasapi.modulos.autenticacao.service.AuthenticationService;
import com.igorbavand.vendasapi.modulos.autenticacao.service.TokenService;
import com.igorbavand.vendasapi.modulos.autenticacao.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AuthenticationService service;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {

        userService.findByLogin(authenticationDto.login());
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @GetMapping("check-user")
    public ResponseEntity checkUser() {
        return ResponseEntity.ok().body(service.getAuthenticatedUser());
    }
}
