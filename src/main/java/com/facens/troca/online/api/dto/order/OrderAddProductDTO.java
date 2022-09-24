package com.facens.troca.online.api.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class OrderAddProductDTO {
    private Long orderId;
    private Long productId;
    private Integer amount;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
}
