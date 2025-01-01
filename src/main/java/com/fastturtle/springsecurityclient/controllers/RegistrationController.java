package com.fastturtle.springsecurityclient.controllers;

import com.fastturtle.springsecurityclient.dtos.AppUserDTO;
import com.fastturtle.springsecurityclient.dtos.AuthorityDTO;
import com.fastturtle.springsecurityclient.dtos.RolesDTO;
import com.fastturtle.springsecurityclient.exceptions.UserNotFoundException;
import com.fastturtle.springsecurityclient.models.AppUser;
import com.fastturtle.springsecurityclient.models.Authority;
import com.fastturtle.springsecurityclient.models.Role;
import com.fastturtle.springsecurityclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public AppUserDTO registerUser(@RequestBody AppUser user) {
        AppUser registeredUser = userService.registerUser(
                user.getFname(),
                user.getLname(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles(),
                user.getAuthorities());

        return from(registeredUser);
    }

    @GetMapping("/find")
    public List<AppUserDTO> fetchUsersByRole(@RequestParam("role") String role) {
        role = role.toUpperCase();
        List<AppUser> users = userService.fetchUsersByRole(role);

        if(users.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        List<AppUserDTO> filteredUsers = new ArrayList<>();

        users.forEach(user -> filteredUsers.add(from(user)));

        return filteredUsers;

    }

    private AppUserDTO from(AppUser appUser) {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setFname(appUser.getFname());
        appUserDTO.setLname(appUser.getLname());
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setEmail(appUser.getEmail());

        for(Role role : appUser.getRoles()) {
            appUserDTO.addRole(new RolesDTO(role.getName()));
        }

        for(Authority authority : appUser.getAuthorities()) {
            appUserDTO.addAuthority(new AuthorityDTO(authority.getName()));
        }

        return appUserDTO;
    }
}
