package com.genuinecoder.SpringSecurityDemo;

import com.genuinecoder.SpringSecurityDemo.model.MyUser;
import com.genuinecoder.SpringSecurityDemo.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String[] rolesArray = user.getRole().split(",");
        return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(rolesArray)
                    .build();

    }
}
