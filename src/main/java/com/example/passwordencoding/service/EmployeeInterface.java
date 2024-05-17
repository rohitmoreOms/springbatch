package com.example.passwordencoding.service;

import com.example.passwordencoding.model.request.EmployeeRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeInterface {
    Object saveOrUpdateEmp(EmployeeRequest employeeRequest, Long companyId) throws Exception;

    Object deleteByEmployeeId(Long employeeId) throws Exception;

    Object getAllEmployee(Pageable pageable, String search);

    Object getEmployeeByCompany(String companyName) throws Exception;

    Object getEmployeeNameByEmployeeId(Long employeeId);

    Object searchUserName(String userName);


    Object saveOrUpdate(EmployeeRequest employeeRequest, Long companyId);
}
