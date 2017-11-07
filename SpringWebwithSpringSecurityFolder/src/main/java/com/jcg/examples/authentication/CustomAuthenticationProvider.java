package com.jcg.examples.authentication;


import java.util.ArrayList;
import java.util.List;

import com.jcg.examples.models.User;
import com.jcg.examples.services.UserService;
import com.jcg.examples.services.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService = new UserServiceImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user;

        if ((user = authorizedUser(userName)) != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            if (user.getIs_admin().equals("user")) {
                grantedAuths.add(() -> "ROLE_USER");
            }
            if (user.getIs_admin().equals("admin")) {
                grantedAuths.add(() -> "ROLE_ADMIN");
            }
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
            System.out.println(auth.getAuthorities().toString());
            return auth;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
        }
    }

    private User authorizedUser(String userName) {
        User user;
        try {
            user = userService.findByLog(userName);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return user;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}