package com.facens.troca.online.api.service.authentication;

import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmailIgnoreCase(email);

        if(user.isPresent()) {
            return user.get();
        }

        throw new BadCredentialsException("Wrong email or password");
    }
}
