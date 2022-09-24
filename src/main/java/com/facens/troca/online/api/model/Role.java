package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.role.RoleRegisterDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Role")
@Getter @Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    private String name;

    public Role(){}
    public Role(RoleRegisterDTO dto) {
        this.name=dto.getName();
    }
}
