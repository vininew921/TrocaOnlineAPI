package com.facens.troca.online.api.dto.order;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class OrderProductOutDTO {
    private Long id;
    private UserOutDTO owner;
    private Integer amount;
    private Category category;
    private String title;
    private String description;
    private BigDecimal value;
    private Integer maxDays;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    private Boolean isSale;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderStartDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderEndDate;


    public OrderProductOutDTO(UserOutDTO userOutDTO, Category cat, Product prod, Integer amount,LocalDate orderStartDate, LocalDate orderEndDate) {
        this.id=prod.getId();
        this.owner=userOutDTO;
        this.amount=amount;
        this.category=cat;
        this.title=prod.getTitle();
        this.description=prod.getDescription();
        this.value=prod.getValue();
        this.maxDays=prod.getMaxDays();
        this.startDate=prod.getStartDate();
        this.endDate=prod.getEndDate();
        this.isSale=prod.getIsSale();
        this.orderStartDate=orderStartDate;
        this.orderEndDate=orderEndDate;
    }
}
