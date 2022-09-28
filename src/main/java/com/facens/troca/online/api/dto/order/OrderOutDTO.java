package com.facens.troca.online.api.dto.order;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderOutDTO {
    private Long id;
    private UserOutDTO buyer;
    private List<OrderProductOutDTO> products= new ArrayList<>();
    private BigDecimal total;

    public void addItem(UserOutDTO userOutDTO, Category cat, Product prod, Integer amount, LocalDate orderStartDate, LocalDate orderEndDate){
        products.add(new OrderProductOutDTO(userOutDTO, cat, prod, amount,orderStartDate, orderEndDate));
    }
}
