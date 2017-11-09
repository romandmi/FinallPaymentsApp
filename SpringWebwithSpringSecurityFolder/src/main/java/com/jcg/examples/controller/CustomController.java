package com.jcg.examples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcg.examples.models.User;
import com.jcg.examples.services.UserService;
import com.jcg.examples.services.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 *
 * Controller for user login and logout. Also included solution for redirecting to the
 * right URL using information about the role in the system.
 *
 */
@Controller
public class CustomController {

    /**
     * This is the main method which helps to select path according to role
     * @param request get request from /welcome page
     * @return String redirect to home page for admin/user
     */

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeUser(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER"))
            return "redirect:/user";
        if (request.isUserInRole("ROLE_ADMIN"))
                return "redirect:/admin";
        return "redirect:/login";
    }

    /**
     * This is the method which helps to see login page
     * @param model ModelMap uses when building model data for use with UI tools
     * @return String login page name
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    /**
     * This is the method which helps to logout from the system
     * @param request HttpServletRequest the request being served
     * @param response HttpServletResponse the response being generated
     * @return redirect to login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }


}
