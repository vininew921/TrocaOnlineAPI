package com.facens.troca.online.api.dto.user;

import com.facens.troca.online.api.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserOutDTO {
    private Long   id;
    private String name;
    private String email;
    private String photoUrl;

    public UserOutDTO(User user) {
        this.id= user.getId();
        this.name= user.getName();
        this.email= user.getEmail();
        this.photoUrl=getPhotoUrl();
    }
}
