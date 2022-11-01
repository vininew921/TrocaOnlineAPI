package com.facens.troca.online.api.dto.product.image;

import com.facens.troca.online.api.model.ProductImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageOutDTO {

    private String title;
    private String imageUrl;

    public ProductImageOutDTO(ProductImage productImage) {
        this.title = productImage.getTitle();
        this.imageUrl = productImage.getImageUrl();
    }
}
