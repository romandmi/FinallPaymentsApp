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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService = new UserServiceImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user;
        if ((user = authorizedUser(userName, password)) != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            GrantedAuthority grantedAuthority;
            if (user.getIs_admin().equals("user"))
                grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            else
                grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantedAuths.add(grantedAuthority);
            return new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
        }
    }

    private User authorizedUser(String userName, String password) {
        User user;
        try {
            user = userService.findByLog(userName);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return null;
        }
        if (user.getPassword().equals(password))
            return user;
        else
            return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}