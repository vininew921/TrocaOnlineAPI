package com.facens.troca.online.api.controller;

import com.facens.troca.online.api.dto.order.OrderAddProductDTO;
import com.facens.troca.online.api.dto.order.OrderOutDTO;
import com.facens.troca.online.api.dto.order.OrderRegisterDTO;
import com.facens.troca.online.api.dto.order.OrderRemoveProductDTO;
import com.facens.troca.online.api.service.OrderProductService;
import com.facens.troca.online.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final String ID_ENDPOINT = "/{id}";
    @Autowired private OrderService service;
    @Autowired private OrderProductService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderOutDTO> getById(@PathVariable Long id ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/open")
    public ResponseEntity<OrderOutDTO> open(@Valid @RequestBody OrderRegisterDTO dto){
        OrderOutDTO order = service.openOrder(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID_ENDPOINT).buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    //==================================================================================================================

    @PostMapping("/add")
    public ResponseEntity<OrderOutDTO> addItem(@RequestBody OrderAddProductDTO dto){
        OrderOutDTO order = itemService.addProduct(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID_ENDPOINT).buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<OrderOutDTO> removeItem(@RequestBody OrderRemoveProductDTO dto){
        OrderOutDTO order = itemService.removeProduct(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID_ENDPOINT).buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

}
