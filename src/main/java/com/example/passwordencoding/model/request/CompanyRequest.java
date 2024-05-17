package com.example.passwordencoding.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyRequest {

    private Long companyId;
    private String companyName;
    private String companyAddress;
    private String pinCode;
    private String mobileNo;
    private String state;
    private String city;
    private String website;
    private String employeeSize;
    private String companyType;
}
