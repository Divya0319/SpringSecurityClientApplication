package com.fastturtle.springsecurityclient.services;

import com.fastturtle.springsecurityclient.models.AppUser;
import com.fastturtle.springsecurityclient.models.CustomUserDetails;
import com.fastturtle.springsecurityclient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(userOptional.get());
    }
}
