package com.portal.company.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "company_table")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "companyId")
    private Integer companyId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "address")
    private String address;

    public Company(String companyName, String address) {
        this.companyName = companyName;
        this.address = address;
    }
}