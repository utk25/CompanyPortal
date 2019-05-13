package com.portal.company.service;

import com.portal.company.dao.UsersDAO;
import com.portal.company.exception.UnauthenticatedException;
import com.portal.company.exception.UserLoggedOutException;
import com.portal.company.model.Login;
import com.portal.company.model.User;
import com.portal.company.model.UserAuthToken;
import com.portal.company.model.UserProfile;
import com.portal.company.repository.UserRepository;
import com.portal.company.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersDAO usersDAO;

    public UserAuthToken isValidUser(Login login) {
        User user =  getUser(login.getUsername(), login.getPassword());

        if (user == null) {
            throw new UnauthenticatedException("Username/Password is invalid");
        }
        UserAuthToken userAuthToken = new UserAuthToken();
        userAuthToken.setAuthToken(user.getCompany().getCompanyId() + ":" + user.getUsername());
        return userAuthToken;
    }

    public UserProfile getUserProfile(UserAuthToken userAuthToken) {
        Boolean isUserLoggedIn = usersDAO.isUserLoggedIn(userAuthToken);
        if (!isUserLoggedIn) {
            throw new UserLoggedOutException("User is not logged in");
        }
        User user = getUser(Utility.getUserNameFromAuthToken(userAuthToken));
        UserProfile userProfile = new UserProfile();
        userProfile.setName(user.getName());
        userProfile.setJobRole(user.getJobRole());
        userProfile.setCompanyName(user.getCompany().getCompanyName());
        return userProfile;
    }

    private User getUser(String username, String password) {
        return userRepository.findUserByUsername(username, password);
    }

    private User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

}
