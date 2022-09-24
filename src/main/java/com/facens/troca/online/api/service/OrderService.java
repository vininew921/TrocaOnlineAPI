package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.order.OrderRegisterDTO;
import com.facens.troca.online.api.model.Order;
import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {
    @Autowired private OrderRepository repository;
    @Autowired private UserService userService;

    public Order openOrder(OrderRegisterDTO dto) {
        User user = userService.getByIdRaw(dto.getUserId());
        Order order = new Order();
        order.setUser(user);
        order = repository.save(order);
        return order;
    }

    public Order getByIdRaw(Long id) {
        return repository.findById(id).orElseThrow(() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "None Order was found by the informed id");
        });
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided Category was not found.");
        }
    }
}
