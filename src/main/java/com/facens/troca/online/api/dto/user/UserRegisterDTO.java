package com.facens.troca.online.api.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterDTO {

    @NotNull(message = "Username must be not null")
    @Size(max = 50, message = "Username length must be less than 50")
    private String username;
    @NotNull(message = "Email must be not null")
    @Size(max = 256, message = "Email length must be less than 256")
    @Email(message = "Email must be valid")
    private String email;
    @NotNull(message = "Password must be not null")
    @Size(min = 6, max = 256, message = "Password length must be between 6 and 256")
    private String password;
    private String photoUrl;

    public String getEncodedPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
