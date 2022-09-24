package com.facens.troca.online.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter @Setter
public class User {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @OneToOne
    @JoinColumn(name="roleid", nullable = false)
    private Role   role;

    private String name;
    private String email;
    private String password;
    private String photoUrl;

}
