package com.portal.company.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "jobRole")
    private String jobRole;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_Id", referencedColumnName = "companyId")
    private Company company;

    public User(String username, String password, String name, String jobRole, Company company) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.jobRole = jobRole;
        this.company = company;
    }
}