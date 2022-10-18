package com.facens.troca.online.api.config.security;

import com.facens.troca.online.api.repository.UserRepository;
import com.facens.troca.online.api.service.authentication.AuthenticationService;
import com.facens.troca.online.api.service.authentication.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private static final String[] AUTH_WHITELIST = {
            "/auth",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder encoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authenticationService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST,  "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/products/search/**").permitAll()
                .antMatchers(HttpMethod.POST, "/orders").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/users").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/users").hasAuthority("USER")
                .antMatchers("/orders/**", "/products/**").hasAuthority("USER")
                .antMatchers("/categories/**", "/roles/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenService, userRepository),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
