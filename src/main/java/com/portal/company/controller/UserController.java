package com.portal.company.controller;

import com.portal.company.config.RedisConfig;
import com.portal.company.dao.UsersDAO;
import com.portal.company.model.*;
import com.portal.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public UserAuthToken isValidUser(@RequestBody Login login) {
        UserAuthToken userAuthToken = userService.isValidUser(login);
        usersDAO.saveUser(userAuthToken);
        return userAuthToken;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public UserProfile showUserDetails(@RequestHeader(value = "authToken") String userAuthToken) {

        return userService.getUserProfile(UserAuthToken.builder().authToken(userAuthToken).build());
    }
}