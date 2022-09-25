package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.order.OrderOutDTO;
import com.facens.troca.online.api.dto.order.OrderRegisterDTO;
import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.model.Order;
import com.facens.troca.online.api.model.OrderProduct;
import com.facens.troca.online.api.model.Product;
import com.facens.troca.online.api.model.User;
import com.facens.troca.online.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class OrderService {
    @Autowired private OrderRepository repository;
    @Autowired private UserService userService;

    public OrderOutDTO fillOrderDetails(Order order){
        OrderOutDTO response = new OrderOutDTO();
        response.setId(order.getId());
        response.setBuyer(new UserOutDTO(order.getUser()));
        response.setTotal(calcTotal(order));
        if(order.getItems() != null)
            for(OrderProduct item : order.getItems()){
                Product prod = item.getId().getProduct();
                response.addItem(new UserOutDTO(prod.getUser()), prod.getCategory(), prod, item.getAmount(), item.getStartDate(), item.getEndDate());
            }
        return response;
    }

    public OrderOutDTO openOrder(OrderRegisterDTO dto) {
        User user = userService.getByIdRaw(dto.getUserId());
        Order order = new Order();
        order.setUser(user);
        order = repository.save(order);
        return fillOrderDetails(order);
    }

    public OrderOutDTO getById(Long id) {
        Order order = getByIdRaw(id);
        return fillOrderDetails(order);
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

    public BigDecimal calcTotal(Order order){
        BigDecimal total = new BigDecimal("0");
        if(order.getItems() != null)
            for(OrderProduct item : order.getItems()){
                Product prod = item.getId().getProduct();
                BigDecimal subtotal = prod.getValue();
                subtotal = subtotal.multiply(BigDecimal.valueOf(item.getAmount().intValue()));
                total = total.add(subtotal);
            }
        return total;
    }
}
