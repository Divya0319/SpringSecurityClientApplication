package com.fastturtle.springsecurityclient.dtos;

import com.fastturtle.springsecurityclient.models.Authority;
import com.fastturtle.springsecurityclient.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class AppUserDTO {

    private String fname;

    private String lname;

    private String username;

    private String email;

    private List<Role> roles;

    private List<Authority> authorities;
}
