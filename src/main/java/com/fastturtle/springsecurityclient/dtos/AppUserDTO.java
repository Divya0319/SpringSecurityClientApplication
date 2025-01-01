package com.fastturtle.springsecurityclient.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AppUserDTO {

    private String fname;

    private String lname;

    private String username;

    private String email;

    private List<RolesDTO> roles;

    private List<AuthorityDTO> authorities;

    public void addRole(RolesDTO rolesDTO) {
        if(roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(rolesDTO);

    }

    public void addAuthority(AuthorityDTO authorityDTO) {
        if(authorities == null) {
            authorities = new ArrayList<>();
        }

        authorities.add(authorityDTO);

    }
}
