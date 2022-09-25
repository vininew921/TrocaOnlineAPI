package com.facens.troca.online.api.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private Long roleId;
    private String email;
    private String password;
    private String photoUrl;

    public String getEncodedPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
