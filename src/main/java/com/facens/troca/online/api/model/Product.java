package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.product.ProductRegisterDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate startDate;
    @Column(name="enddate")
    private LocalDate endDate;
    @Column(name="issale")
    private Boolean isSale;

    public Product(User user, Category cat, ProductRegisterDTO dto) {
        this.user=user;
        this.category=cat;
        this.title=dto.getTitle();
        this.description=dto.getDescription();
        this.value=dto.getValue();
        this.maxDays=dto.getMaxDays();
        this.startDate=dto.getStartDate();
        this.endDate=dto.getEndDate();
        this.isSale=dto.getIsSale();
    }
}
