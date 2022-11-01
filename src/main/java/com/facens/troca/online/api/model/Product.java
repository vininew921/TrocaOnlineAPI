package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.product.ProductRegisterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;
    private String title;
    private String description;
    @NumberFormat(pattern = "#.##0.00")
    private BigDecimal value;
    @Column(name = "maxdays")
    private Integer maxDays;
    @Column(name = "startdate")
    private LocalDate startDate;
    @Column(name = "enddate")
    private LocalDate endDate;
    @Column(name = "issale")
    private Boolean isSale;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductImage> productImages;

    public Product(User user, Category cat, ProductRegisterDTO dto) {
        this.user = user;
        this.category = cat;
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.value = dto.getValue();
        this.maxDays = dto.getMaxDays();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.isSale = dto.getIsSale();
        this.productImages = null; // TODO: adição de imagens aos produtos
    }
}
