package com.fastturtle.springsecurityclient.services;

import com.fastturtle.springsecurityclient.models.AppUser;
import com.fastturtle.springsecurityclient.models.Status;
import com.fastturtle.springsecurityclient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        AppUser user = userOptional.get();
        boolean userEnabled = userOptional.get().getStatus() == Status.ACTIVE;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        user.getAuthorities().forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName())));

        return new User(
                user.getEmail(),
                user.getPassword(),
                userEnabled,
                true,
                true,
                true,
                grantedAuthorities
        );
    }
}
