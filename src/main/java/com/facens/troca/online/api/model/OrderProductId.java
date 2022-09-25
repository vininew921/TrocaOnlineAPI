package com.facens.troca.online.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Setter @Getter
public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 2L;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    public OrderProductId(){}
    public OrderProductId(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
