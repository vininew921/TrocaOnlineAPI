package com.facens.troca.online.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="Orders")
@Getter @Setter
public class Order implements Serializable {
    private static final long serialVersionUID = 2232332L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User   user;

    @NumberFormat(pattern = "#.##0.00")
    @Column(name="totalvalue")
    private BigDecimal totalValue;

    public Order(){
        // public
    }
}
