package com.facens.troca.online.api.controller;

import com.facens.troca.online.api.dto.product.ProductOutDTO;
import com.facens.troca.online.api.dto.product.ProductRegisterDTO;
import com.facens.troca.online.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<List<ProductOutDTO>> getAllFilteredByName(
            @RequestParam(required = false,defaultValue = "ASC") String order,
            @RequestParam(required = false,defaultValue = "") String title,
            @RequestParam(required = false,defaultValue = "10") Integer size,
            @RequestParam(required = false,defaultValue = "0") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(order.trim()), "title");
        List<ProductOutDTO> products = service.getAllByPagination(pageRequest,  ""+title.trim());

        return ResponseEntity.ok().body(products);
    }
}
