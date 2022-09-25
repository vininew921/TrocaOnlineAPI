package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.role.RoleRegisterDTO;
import com.facens.troca.online.api.model.Role;
import com.facens.troca.online.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role insert(RoleRegisterDTO dto) {
        Role role = new Role(dto);
        role = repository.save(role);
        return role;
    }

    public Role getByIdRaw(Long id) {
        return repository.findById(id).orElseThrow(() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None Role was found by the informed id");
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
