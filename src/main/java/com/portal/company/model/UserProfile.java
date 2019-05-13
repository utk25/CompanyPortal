package com.portal.company.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfile {

    private String name;
    private String jobRole;
    private String companyName;
}
