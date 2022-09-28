package com.facens.troca.online.api.service;

import com.facens.troca.online.api.dto.order.OrderAddProductDTO;
import com.facens.troca.online.api.dto.order.OrderOutDTO;
import com.facens.troca.online.api.dto.order.OrderRemoveProductDTO;
import com.facens.troca.online.api.model.Order;
import com.facens.troca.online.api.model.OrderProduct;
import com.facens.troca.online.api.model.OrderProductId;
import com.facens.troca.online.api.model.Product;
import com.facens.troca.online.api.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderProductService {
    @Autowired private OrderProductRepository repository;
    @Autowired private OrderService orderService;
    @Autowired private ProductService productService;
    @Autowired private UserService userService;

    public OrderOutDTO addProduct(OrderAddProductDTO dto) {
        Order order = orderService.getByIdRaw(dto.getOrderId());
        Product product = productService.getByIdRaw(dto.getProductId());

        OrderProduct item = new OrderProduct();
        item.setId(new OrderProductId(order,product));
        item.setAmount(dto.getAmount());
        item.setStartDate(dto.getStartDate());
        item.setEndDate(dto.getEndDate());

        repository.save(item);
        order = orderService.getByIdRaw(dto.getOrderId());

        return orderService.fillOrderDetails(order);
    }

    public OrderOutDTO removeProduct(OrderRemoveProductDTO dto) {
        Order order = orderService.getByIdRaw(dto.getOrderId());
        Product product = productService.getByIdRaw(dto.getProductId());
        try {
            repository.deleteById(new OrderProductId(order,product));
            order = orderService.getByIdRaw(dto.getOrderId());
            return orderService.fillOrderDetails(order);
        } catch (EmptyResultDataAccessException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided Item was not found.");
        }
    }
}
