package com.facens.troca.online.api.controller;

import com.facens.troca.online.api.dto.role.RoleRegisterDTO;
import com.facens.troca.online.api.model.Role;
import com.facens.troca.online.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired private RoleService service;

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id ) {
        return ResponseEntity.ok(service.getByIdRaw(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@Valid @RequestBody RoleRegisterDTO dto){
        Role role = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getId()).toUri();
        return ResponseEntity.created(uri).body(role);
    }

}
