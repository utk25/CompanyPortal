package com.portal.company.repository;

import com.portal.company.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer>{
    @Query("select company from Company company " +
            "where company.id = :id")
    Company findCompanyById(@Param("id") Integer id);
}
