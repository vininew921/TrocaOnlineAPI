package com.facens.troca.online.api.controller;

import com.facens.troca.online.api.dto.category.CategoryRegisterDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id ) {
        return ResponseEntity.ok(service.getByIdRaw(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRegisterDTO dto){
        Category category = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

}
