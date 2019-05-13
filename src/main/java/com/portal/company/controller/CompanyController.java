package com.portal.company.controller;

import com.portal.company.model.CompanyProfile;
import com.portal.company.model.UserAuthToken;
import com.portal.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, value = "/company")
    public CompanyProfile showCompanyDetails(@RequestHeader(value = "authToken") String userAuthToken) {
        return companyService.getCompanyProfile(UserAuthToken.builder().authToken(userAuthToken).build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ping")
    public CompanyProfile setAlive(@RequestHeader(value = "authToken") String userAuthToken) {
        return companyService.setAlive(UserAuthToken.builder().authToken(userAuthToken).build());
    }
}