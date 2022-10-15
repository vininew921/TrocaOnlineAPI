package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.dto.user.UserRegisterDTO;
import com.facens.troca.online.api.exceptionhandler.exceptions.UniqueEmailException;
import com.facens.troca.online.api.exceptionhandler.exceptions.UniqueUsernameException;
import com.facens.troca.online.api.model.Role;
import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleService roleService;

    public UserOutDTO insert(UserRegisterDTO inUser) {
        validateEmail(inUser);
        validateUsername(inUser);
        Role role = roleService.getByIdRaw(2L);
        User user = new User(inUser, role);
        user = repository.save(user);
        return new UserOutDTO(user);
    }

    public UserOutDTO getById(Long id) {
        return new UserOutDTO(repository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None User was found by the informed id");
        }));
    }

    public User getByIdRaw(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None User was found by the informed id");
        });
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided User was not found.");
        }
    }

    private void validateEmail(UserRegisterDTO userDTO) {
        Optional<User> userByEmail = repository.findByEmailIgnoreCase(userDTO.getEmail());

        if (userByEmail.isPresent()) {
            throw new UniqueEmailException("During->"+Thread.currentThread().getStackTrace());
        }
    }

    private void validateUsername(UserRegisterDTO userDTO) {
        Optional<User> userByUsername = repository.findByUsernameIgnoreCase(userDTO.getUsername());

        if (userByUsername.isPresent()) {
            throw new UniqueUsernameException("During->"+Thread.currentThread().getStackTrace());
        }
    }
}
