package com.goit.todolist.note.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = customUserDetailsService.loadUserByUsername(username);
        return checkPassword(user, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(UserDetails user, String rawPassword){
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            User innerUser = new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );

            return new UsernamePasswordAuthenticationToken(innerUser, user.getPassword(), user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
