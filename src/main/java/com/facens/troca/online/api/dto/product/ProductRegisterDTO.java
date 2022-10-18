package com.facens.troca.online.api.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class ProductRegisterDTO {
    private Long idUser;
    private Long idCategory;
    private String title;
    private String description;
    private BigDecimal value;
    private Integer maxDays;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    private Boolean isSale;
    private String image;
}
