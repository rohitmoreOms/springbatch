package com.example.passwordencoding.service;

import com.example.passwordencoding.model.request.CompanyRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public interface CompanyInterface {
    Object saveOrUpdateCompany(CompanyRequest companyRequest) throws Exception;

    Object getAllCompany( Pageable pageable, String search);

    Object deleteByCompanyId(Long companyId) throws Exception;

    Object importExcel(MultipartFile file) throws Exception;

    Object importExcelFile(MultipartFile file) throws Exception;

    InputStream exportProduct();
}
