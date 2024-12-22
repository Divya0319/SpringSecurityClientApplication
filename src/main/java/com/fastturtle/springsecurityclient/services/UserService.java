package com.fastturtle.springsecurityclient.services;

import com.fastturtle.springsecurityclient.models.AppUser;
import com.fastturtle.springsecurityclient.models.Status;
import com.fastturtle.springsecurityclient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser registerUser(String fname, String lname, String username, String password, String email, String role) {
        AppUser appUser = new AppUser();
        appUser.setFname(fname);
        appUser.setLname(lname);
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setRole(role);

        appUser.setStatus(Status.ACTIVE);

        return userRepository.save(appUser);
    }
}
