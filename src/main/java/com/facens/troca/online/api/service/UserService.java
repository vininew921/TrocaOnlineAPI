package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.dto.user.UserRegisterDTO;
import com.facens.troca.online.api.dto.user.UserUpdateDTO;
import com.facens.troca.online.api.exceptionhandler.exceptions.UniqueEmailException;
import com.facens.troca.online.api.exceptionhandler.exceptions.UniqueUsernameException;
import com.facens.troca.online.api.model.Role;
import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.UserRepository;
import com.facens.troca.online.api.service.authentication.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final TokenService tokenService;

    public UserOutDTO insert(UserRegisterDTO inUser) {
        Role role = roleService.getByIdRaw(2L);
        User user = new User(inUser, role);
        validateEmail(user);
        validateUsername(user);
        user = repository.save(user);
        return new UserOutDTO(user);
    }

    public UserOutDTO update(UserUpdateDTO userDTO, String token) {
        User user = getByIdRaw(tokenService.getUserId(token));
        validateUpdate(userDTO, user);
        user = repository.save(user);
        return new UserOutDTO(user);
    }

    public UserOutDTO getByToken(String token) {
        return getById(tokenService.getUserId(token));
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

    private void validateUpdate(UserUpdateDTO userDTO, User user) {
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPhotoUrl(userDTO.getPhotoUrl());
        validateEmail(user);
        validateUsername(user);
    }

    private void validateEmail(User user) {
        Optional<User> userByEmail = repository.findByEmailIgnoreCase(user.getEmail());
        if (userByEmail.isPresent() && !user.equals(userByEmail.get())) {
            throw new UniqueEmailException("During->" + Arrays.toString(Thread.currentThread().getStackTrace()));
        }
    }

    private void validateUsername(User user) {
        Optional<User> userByUsername = repository.findByUsernameIgnoreCase(user.getUsername());

        if (userByUsername.isPresent() && !user.equals(userByUsername.get())) {
            throw new UniqueUsernameException("During->" + Arrays.toString(Thread.currentThread().getStackTrace()));
        }
    }
}
