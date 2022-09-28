package com.facens.troca.online.api.config.security;

import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.UserRepository;
import com.facens.troca.online.api.service.authentication.TokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        if (tokenService.isTokenValid(token)) {
            authenticateUser(token);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(t -> t.startsWith("Bearer "))
                .map(t -> t.substring(7))
                .orElse(null);
    }

    @SneakyThrows
    private void authenticateUser(String token) {
        Long id = tokenService.getUserId(token);
        User user = userRepository.findById(id).orElseThrow(() -> new AuthenticationServiceException("User not found"));
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}