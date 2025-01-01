package com.fastturtle.springsecurityclient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDTO {

    private String name;

    public AuthorityDTO(String name) {
        this.name = name;
    }
}
