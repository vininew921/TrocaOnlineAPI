package com.facens.troca.online.api.repository;

import com.facens.troca.online.api.model.OrderProduct;
import com.facens.troca.online.api.model.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
