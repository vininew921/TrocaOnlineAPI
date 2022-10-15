package com.facens.troca.online.api.repository;

import com.facens.troca.online.api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
    @Query("SELECT p FROM Product p " +
            "WHERE " +
            " LOWER(p.title)   LIKE   LOWER(CONCAT('%', ?1, '%'))")
    public Page<Product> findAllOrdered(Pageable pageRequest, String title);
}
