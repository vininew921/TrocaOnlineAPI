package com.facens.troca.online.api.dto.product;

import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class ProductOutDTO {
    private Long id;
    private UserOutDTO owner;
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


    public ProductOutDTO(UserOutDTO userOutDTO, Category cat, Product prod) {
        this.id=prod.getId();
        this.owner=userOutDTO;
        this.category=cat;
        this.title=prod.getTitle();
        this.description=prod.getDescription();
        this.value=prod.getValue();
        this.maxDays=prod.getMaxDays();
        this.startDate=prod.getStartDate();
        this.endDate=prod.getEndDate();
        this.isSale=prod.getIsSale();
    }
}
