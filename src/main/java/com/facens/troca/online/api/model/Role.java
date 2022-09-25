package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.role.RoleRegisterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Role")
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role(RoleRegisterDTO dto) {
        this.name = dto.getName();
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
