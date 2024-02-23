package com.igorbavand.vendasapi.modulos.autenticacao.service;

import com.igorbavand.vendasapi.modulos.autenticacao.dto.UserResponseDto;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByLogin(username);
    }

    @SuppressWarnings({"checkstyle:methodlength"})
    public UserResponseDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            var user = userService.findByLogin(((UserDetails) principal).getUsername());
            return new UserResponseDto(user.getId(),
                user.getNome(),
                user.getUsername(),
                user.getTelefone(),
                user.getCpf(),
                user.getCidade(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt());
        } else {
            throw new NotFoundException("User not found.");
        }
    }
}

