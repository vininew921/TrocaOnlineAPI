package com.facens.troca.online.api.controller;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.dto.user.UserRegisterDTO;
import com.facens.troca.online.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserOutDTO> getById(@PathVariable Long id ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserOutDTO> addUser(@Valid @RequestBody UserRegisterDTO dto){
        UserOutDTO user = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

}
