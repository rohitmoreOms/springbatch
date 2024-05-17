package com.example.passwordencoding.model.request;

import com.example.passwordencoding.model.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class EmployeeRequest {
    private Long employeeId;
    private String employeeName;
    private String address;
    private String jobRole;
    private String email;
    private String salary;
    private String mobileNo;
    private String pinCode;
    private String state;
    private String city;
    private String country;
    private String employeeLastName;
    private MultipartFile file;
}
