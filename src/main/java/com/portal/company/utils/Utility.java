package com.portal.company.utils;

import com.portal.company.model.UserAuthToken;

public class Utility {

    public static String getUserNameFromAuthToken(UserAuthToken userAuthToken) {
        return userAuthToken.getAuthToken().split(":")[1];
    }

    public static Integer getCompanyIdFromAuthToken(UserAuthToken userAuthToken) {
        return Integer.parseInt(userAuthToken.getAuthToken().split(":")[0]);
    }
}
