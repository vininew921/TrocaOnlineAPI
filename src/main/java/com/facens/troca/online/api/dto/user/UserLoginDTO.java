package com.facens.troca.online.api.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserLoginDTO {

    @NotNull(message = "Email must be not null")
    private String email;
    @NotNull(message = "Password must be not null")
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
