package com.facens.troca.online.api.model;

import com.facens.troca.online.api.dto.user.UserRegisterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Inform an username")
    @NotBlank(message = "Inform an username")
    private String username;

    @NotEmpty(message = "Inform an e-mail")
    @NotBlank(message = "Inform an e-mail")
    private String email;

    @NotEmpty(message = "Inform a password")
    @NotBlank(message = "Inform a password")
    private String password;
    private String photoUrl;
    @OneToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    public User(UserRegisterDTO inUser, Role role) {
        this.role = role;
        this.username = inUser.getUsername();
        this.email = inUser.getEmail();
        this.password = inUser.getEncodedPassword();
        this.photoUrl = inUser.getPhotoUrl();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
