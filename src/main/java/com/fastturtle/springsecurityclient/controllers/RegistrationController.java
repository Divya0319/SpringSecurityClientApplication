package com.fastturtle.springsecurityclient.controllers;

import com.fastturtle.springsecurityclient.dtos.AppUserDTO;
import com.fastturtle.springsecurityclient.models.AppUser;
import com.fastturtle.springsecurityclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                user.getPassword(),
                user.getEmail());

        return from(registeredUser);
    }

    private AppUserDTO from(AppUser appUser) {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setFname(appUser.getFname());
        appUserDTO.setLname(appUser.getLname());
        appUserDTO.setEmail(appUserDTO.getEmail());

        return appUserDTO;
    }
}