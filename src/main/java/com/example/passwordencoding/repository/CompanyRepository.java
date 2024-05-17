package com.example.passwordencoding.repository;

import com.example.passwordencoding.model.Company;
import com.example.passwordencoding.repository.projection.CompanyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long> {
@Query(value = "select company_id as companyId, company_name as companyName,company_address as companyAddress,company_pincode as pinCode, mobile_no as mobileNo, state as state, city as city, website as website, employee_size as employeeSize,company_type as companyType,company_code as companyCode from `company` where company_name like %:search% ",nativeQuery = true)
    Page<CompanyProjection> getCompanyNameContainingIgnoreCase(Pageable pageable, String search);

    @Query(value = "select company_id as companyId, company_name as companyName,company_address as companyAddress,company_pincode as pinCode, mobile_no as mobileNo, state as state, city as city, website as website, employee_size as employeeSize,company_type as companyType,company_code as companyCode from `company` ",nativeQuery = true)
    Page<CompanyProjection> getAllCompany(Pageable pageable);

    @Query(value = "select company_id as companyId, company_name as companyName,company_address as companyAddress,company_pincode as pinCode, mobile_no as mobileNo, state as state, city as city, website as website, employee_size as employeeSize,company_type as companyType,company_code as companyCode from `company` where company_code like %:companyCode% ",nativeQuery = true)
    Company findByCompanyCode(String companyCode);

    @Query(value = "select company_id as companyId, company_name as companyName,company_address as companyAddress,company_pincode as pinCode, mobile_no as mobileNo, state as state, city as city, website as website, employee_size as employeeSize,company_type as companyType,company_code as companyCode from `company` ",nativeQuery = true)
    List<CompanyProjection>getAllCompanys();

    boolean existsByCompanyName(String companyName);

    //  @Query(value = "select company_id as companyId, company_name as companyName,company_address as companyAddress,company_pincode as pinCode, mobile_no as mobileNo, state as state, city as city, website as website, employee_size as employeeSize,company_type as companyType,company_code as companyCode from `company`where company_id =:companyId",nativeQuery = true)
  //  Company getByCompanyId(Long companyId);
}
