package com.facens.troca.online.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="OrderProduct")
@Getter @Setter
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderProductId id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public OrderProduct(){
        //public
    }
}
