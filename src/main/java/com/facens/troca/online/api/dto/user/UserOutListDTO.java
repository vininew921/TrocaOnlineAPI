package com.facens.troca.online.api.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserOutListDTO {
    List<UserOutDTO> list = new ArrayList<>();
}
