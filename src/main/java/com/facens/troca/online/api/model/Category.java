package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.category.CategoryRegisterDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Category")
@Getter @Setter
public class Category {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    private String name;

    public Category(CategoryRegisterDTO inCategory) {
        this.name=inCategory.getName();
    }
}
