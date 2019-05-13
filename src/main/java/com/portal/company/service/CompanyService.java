package com.portal.company.service;

import com.portal.company.dao.CompanyDAO;
import com.portal.company.dao.UsersDAO;
import com.portal.company.exception.UserLoggedOutException;
import com.portal.company.model.Company;
import com.portal.company.model.CompanyProfile;
import com.portal.company.model.UserAuthToken;
import com.portal.company.repository.CompanyRepository;
import com.portal.company.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private CompanyDAO companyDAO;

    public CompanyProfile getCompanyProfile(UserAuthToken userAuthToken) {
        Boolean isUserLoggedIn = usersDAO.isUserLoggedIn(userAuthToken);
        if (!isUserLoggedIn) {
            throw new UserLoggedOutException("User is not logged in");
        }

        companyDAO.addTotalUserToCompany(Utility.getCompanyIdFromAuthToken(userAuthToken).toString(), Utility.getUserNameFromAuthToken(userAuthToken));

        Integer companyId = Utility.getCompanyIdFromAuthToken(userAuthToken);
        Company company = getCompany(companyId);
        companyDAO.addActiveUserToCompany(Utility.getCompanyIdFromAuthToken(userAuthToken).toString(), Utility.getUserNameFromAuthToken(userAuthToken));

        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setCompanyName(company.getCompanyName());
        companyProfile.setAddress(company.getAddress());
        companyProfile.setActiveUsers(companyDAO.getCompanyCount(companyId));
        companyProfile.setTotalUsers(companyDAO.getTotalCompanyCount(companyId));

        return companyProfile;
    }

    public Company getCompany(Integer companyId) {
        return companyRepository.findCompanyById(companyId);
    }

    public CompanyProfile setAlive(UserAuthToken userAuthToken) {
        Boolean isUserLoggedIn = usersDAO.isUserLoggedIn(userAuthToken);
        if (!isUserLoggedIn) {
            throw new UserLoggedOutException("User is not logged in");
        }
        companyDAO.addActiveUserToCompany(Utility.getCompanyIdFromAuthToken(userAuthToken).toString(), Utility.getUserNameFromAuthToken(userAuthToken));
        Integer companyId = Utility.getCompanyIdFromAuthToken(userAuthToken);
        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setActiveUsers(companyDAO.getCompanyCount(companyId));
        companyProfile.setTotalUsers(companyDAO.getTotalCompanyCount(companyId));
        return companyProfile;
    }
}
