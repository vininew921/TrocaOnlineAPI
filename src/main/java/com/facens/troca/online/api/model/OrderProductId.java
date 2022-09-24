package com.facens.troca.online.api.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 2L;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    public OrderProductId(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
