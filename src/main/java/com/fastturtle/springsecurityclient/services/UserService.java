package com.fastturtle.springsecurityclient.services;

import com.fastturtle.springsecurityclient.models.AppUser;
import com.fastturtle.springsecurityclient.models.Authority;
import com.fastturtle.springsecurityclient.models.Role;
import com.fastturtle.springsecurityclient.models.Status;
import com.fastturtle.springsecurityclient.repositories.RoleRepository;
import com.fastturtle.springsecurityclient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser registerUser(String fname, String lname, String username, String password, String email, List<Role> roles, List<Authority> authorities) {
        AppUser appUser = new AppUser();
        appUser.setFname(fname);
        appUser.setLname(lname);
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setRoles(roles);
        appUser.setAuthorities(authorities);

        appUser.setStatus(Status.ACTIVE);

        for(Role role : roles) {
            role.setUser(appUser);
            role.setStatus(Status.ACTIVE);
        }

        appUser.setRoles(roles);

        for(Authority authority : authorities) {
            authority.setUser(appUser);
            authority.setStatus(Status.ACTIVE);
        }

        appUser.setAuthorities(authorities);

        return userRepository.save(appUser);
    }

    public List<AppUser> fetchUsersByRole(String role) {
        List<Role> roles = roleRepository.findByName(role);
        List<AppUser> users = new ArrayList<>();

        if(!roles.isEmpty()) {
            roles.forEach(r -> users.add(r.getUser()));
        }

        return users;
    }
}
