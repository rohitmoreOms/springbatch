package com.example.passwordencoding.repository;

import com.example.passwordencoding.model.Employee;
import com.example.passwordencoding.repository.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "select employee_id as employeeId, employee_name as employeeName, employee_address as address, employee_jobrole as jobRole, email as email, salary as salary, mobile_no as mobileNo, pin_code as pinCode, state as state, city as city, country as country from `employee` where employee_name like %:search% ",nativeQuery = true)
    Page<EmployeeProjection> getEmployeeNameContainingIgnoreCase(Pageable pageable, String search);

    @Query(value = "select employee_id as employeeId, employee_name as employeeName, employee_address as address, employee_jobrole as jobRole, email as email, salary as salary, mobile_no as mobileNo, pin_code as pinCode, state as state, city as city, country as country from `employee` ",nativeQuery = true)
    Page<EmployeeProjection> getAllEmployee(Pageable pageable);

    @Query(value = "SELECT * FROM employee e INNER JOIN company c ON e.company_id = c.company_id WHERE  c.company_name like %:companyName%", nativeQuery = true)
    List<Employee> getEmployeeNameByCompanyName(@Param("companyName") String companyName);
}

