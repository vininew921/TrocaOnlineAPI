package com.facens.troca.online.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegisterDTO {
    private String name;
    private Long roleid;
    private String email;
    private String password;
    private String photoUrl;
}
