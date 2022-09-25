package com.facens.troca.online.api.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterDTO {

    @NotNull(message = "Username must be not null")
    private String username;
    private Long roleId;
    @NotNull(message = "Email must be not null")
    private String email;
    @NotNull(message = "Password must be not null")
    @Size(min = 6, max = 50, message = "Password Length must be between 6 and 50")
    private String password;
    private String photoUrl;

    public String getEncodedPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
