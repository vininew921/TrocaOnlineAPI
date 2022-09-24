package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.user.UserRegisterDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
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

    public User(UserRegisterDTO inUser) {
        this.name=inUser.getName();
        this.email=inUser.getEmail();
        this.password=inUser.getPassword();
        this.photoUrl=inUser.getPhotoUrl();
    }
}
