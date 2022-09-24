package com.facens.troca.online.api.controller;

import com.facens.troca.online.api.dto.product.ProductOutDTO;
import com.facens.troca.online.api.dto.product.ProductRegisterDTO;
import com.facens.troca.online.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutDTO> getById(@PathVariable Long id ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductOutDTO> addProduct(@Valid @RequestBody ProductRegisterDTO dto){
        ProductOutDTO prod = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prod.getId()).toUri();
        return ResponseEntity.created(uri).body(prod);
    }

}
