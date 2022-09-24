package com.facens.troca.online.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="Product")
@Getter @Setter
public class Product {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User   user;

    @OneToOne
    @JoinColumn(name="categoryid", nullable = false)
    private Category category;

    private String title;
    private String description;

    @NumberFormat(pattern = "#.##0.00")
    private BigDecimal value;

    @Column(name="maxdays")
    private Integer maxDays;

    @Column(name="startdate")
    private LocalDateTime startDate;
    @Column(name="enddate")
    private LocalDateTime endDate;
    @Column(name="issale")
    private Boolean isSale;

}
