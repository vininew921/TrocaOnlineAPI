package com.facens.troca.online.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="OrderProduct")
@Getter @Setter
public class OrderProduct {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User   user;

    @NumberFormat(pattern = "#.##0.00")
    @Column(name="totalvalue")
    private BigDecimal totalValue;
}
