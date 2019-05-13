package com.portal.company.dao;

import com.portal.company.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class CompanyDAO {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String totalCompanyViews = "TOTAL_COMPANY_VIEWS_";

    public Integer getCompanyCount(Integer companyId) {
        return redisTemplate.keys(companyId.toString() + "_*").size();
    }

    public void addActiveUserToCompany (String companyId, String userName) {
        redisTemplate.opsForValue().set(companyId + "_" + userName, "1", Constants.REDIS_ACTIVE_USER_COMPANY_EXPIRY,TimeUnit.MINUTES);
    }

    public void addTotalUserToCompany(String companyId, String userName) {
        redisTemplate.opsForValue().set(totalCompanyViews + companyId + "_" + userName, "1");
    }

    public Integer getTotalCompanyCount(Integer companyId) {
        return redisTemplate.keys(totalCompanyViews + companyId + "_*").size();
    }
}
