package com.fastturtle.springsecurityclient.services;

import com.fastturtle.springsecurityclient.models.AppUser;
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

    public AppUser registerUser(String fname, String lname, String password, String email) {
        AppUser appUser = new AppUser();
        appUser.setFname(fname);
        appUser.setLname(lname);
        appUser.setEmail(email);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(appUser);
    }
}
