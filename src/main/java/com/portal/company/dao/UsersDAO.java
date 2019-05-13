package com.portal.company.dao;

import com.portal.company.constants.Constants;
import com.portal.company.model.UserAuthToken;
import com.portal.company.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class UsersDAO {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String user = "USER_ID_";

    public void saveUser(UserAuthToken userAuthToken) {
        String userId = Utility.getUserNameFromAuthToken(userAuthToken);
        redisTemplate.opsForValue().set(user + userId, userAuthToken.getAuthToken(), Constants.REDIS_USER_KEY_EXPIRY_TIME,TimeUnit.MINUTES);
    }

    public boolean isUserLoggedIn(UserAuthToken userAuthToken) {
        String userId = Utility.getUserNameFromAuthToken(userAuthToken);
        String authToken = redisTemplate.opsForValue().get(user + userId);
        return !StringUtils.isEmpty(authToken);
    }
}
