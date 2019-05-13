package com.portal.company.postSetup;

import com.portal.company.model.Company;
import com.portal.company.model.User;
import com.portal.company.repository.CompanyRepository;
import com.portal.company.repository.UserRepository;
import com.portal.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetup implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String...args) throws Exception {
        companyRepository.save(new Company("Pratilipi", "Bengaluru"));
        companyRepository.save(new Company("Pega", "Hyderabad"));
        companyRepository.save(new Company("Amazon", "Seattle"));
        companyRepository.save(new Company("Microsoft", "Redmond"));
        companyRepository.save(new Company("Google", "San Francisco"));

        Company company = companyService.getCompany(1);
        userRepository.save(new User("sandy", "sandy", "Sandeep", "SE", company));

        company = companyService.getCompany(1);
        userRepository.save(new User("amit", "amit", "Amit Kumar", "PSE", company));

        company = companyService.getCompany(2);
        userRepository.save(new User("utk25", "utk25", "Utkarsh", "ASE", company));

        company = companyService.getCompany(3);
        userRepository.save(new User("jeff", "jeff", "Jeff Bezos", "CEO", company));

        company = companyService.getCompany(4);
        userRepository.save(new User("satya", "satya", "Satya Nadela", "CEO", company));

        company = companyService.getCompany(5);
        userRepository.save(new User("sundar", "sundar", "Sundar Pichai", "CEO", company));
    }
}
