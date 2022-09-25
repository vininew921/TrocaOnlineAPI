package com.facens.troca.online.api.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRemoveProductDTO {
    private Long orderId;
    private Long productId;
}
