package com.facens.troca.online.api.dto.product;

import com.facens.troca.online.api.dto.product.image.ProductImageOutDTO;
import com.facens.troca.online.api.dto.user.UserOutDTO;
import com.facens.troca.online.api.model.Category;
import com.facens.troca.online.api.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductOutDTO {
    private Long id;
    private UserOutDTO owner;
    private Category category;
    private String title;
    private String description;
    private BigDecimal value;
    private Integer maxDays;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Boolean isSale;
    private List<ProductImageOutDTO> productImages;

    public ProductOutDTO(Product prod) {
        this.id = prod.getId();
        this.title = prod.getTitle();
        this.description = prod.getDescription();
        this.value = prod.getValue();
        this.maxDays = prod.getMaxDays();
        this.startDate = prod.getStartDate();
        this.endDate = prod.getEndDate();
        this.isSale = prod.getIsSale();
        this.owner = new UserOutDTO(prod.getUser());
        this.category = prod.getCategory();
        this.productImages = getProductImages(prod);
    }

    public ProductOutDTO(UserOutDTO userOutDTO, Category cat, Product prod) {
        this.id = prod.getId();
        this.owner = userOutDTO;
        this.category = cat;
        this.title = prod.getTitle();
        this.description = prod.getDescription();
        this.value = prod.getValue();
        this.maxDays = prod.getMaxDays();
        this.startDate = prod.getStartDate();
        this.endDate = prod.getEndDate();
        this.isSale = prod.getIsSale();
        this.productImages = getProductImages(prod);
    }

    private static List<ProductImageOutDTO> getProductImages(Product prod) {
        return Optional.ofNullable(prod.getProductImages())
                .orElse(Collections.emptyList())
                .stream()
                .map(ProductImageOutDTO::new)
                .collect(Collectors.toList());
    }
}
