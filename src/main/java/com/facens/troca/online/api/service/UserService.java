package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.dto.user.UserRegisterDTO;
import com.facens.troca.online.api.model.Role;
import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired private UserRepository repository;
    @Autowired private RoleService roleService;

    public UserOutDTO insert(UserRegisterDTO inUser) {
        Role role = roleService.getByIdRaw(inUser.getRoleid());
        User user = new User(inUser, role);
        user = repository.save(user);
        return new UserOutDTO(user);
    }

    public UserOutDTO getById(Long id) {
        return new UserOutDTO(repository.findById(id).orElseThrow(() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None User was found by the informed id");
        }));
    }

    public User getByIdRaw(Long id) {
        return repository.findById(id).orElseThrow(() ->{
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
}
