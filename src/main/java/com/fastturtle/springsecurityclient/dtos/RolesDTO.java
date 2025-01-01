package com.fastturtle.springsecurityclient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesDTO {

    private String name;

    public RolesDTO(String name) {
        this.name = name;
    }
}
