package com.jcg.examples.authentication;

import java.util.ArrayList;
import java.util.List;
import com.jcg.examples.models.User;
import com.jcg.examples.services.UserService;
import com.jcg.examples.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * Class that provides user's authentication
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
    private UserService userService = new UserServiceImpl();

    /**
     * This method helps to authenticate user
     * @param authentication authentication
     * @return AuthenticationToken with login, password and role
     * @throws AuthenticationException if authentication failed
     */
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
            logger.error("Authentication failed, can't find user " + userName + " in the database",
                    new AuthenticationCredentialsNotFoundException("invalid credentials"));
            throw new AuthenticationCredentialsNotFoundException("invalid credentials");
        }
    }

    /**
     * This method return user if we can find him
     * @param userName user's login
     * @param password user's password
     * @return user
     */
    private User authorizedUser(String userName, String password) {
        User user;
        try {
            user = userService.findByLog(userName);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return null;
        }
        if (user.getPassword().equals(password))
            return user;
        else
            return null;
    }

    /**
     * This method checks if form is assignable
     * @param authentication authentication
     * @return boolean value
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}