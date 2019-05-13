package com.portal.company.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyProfile {
    private String companyName;
    private String address;
    private Integer activeUsers;
    private Integer totalUsers;
}
