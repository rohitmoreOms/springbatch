package com.example.passwordencoding.service.impl;

import com.example.passwordencoding.model.Company;
import com.example.passwordencoding.model.PageDto;
import com.example.passwordencoding.model.request.CompanyRequest;
import com.example.passwordencoding.repository.CompanyRepository;
import com.example.passwordencoding.repository.projection.CompanyProjection;
import com.example.passwordencoding.service.CompanyInterface;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CompanyService implements CompanyInterface {

    @Autowired
    CompanyRepository companyRepository;

    private static String number = "1111";

    @Override
    public Object saveOrUpdateCompany(CompanyRequest companyRequest) throws Exception {
        try {
            if (companyRepository.existsById(companyRequest.getCompanyId())) {
                Company company = companyRepository.findById(companyRequest.getCompanyId()).get();
                company.setCompanyName(companyRequest.getCompanyName());
                company.setCity(companyRequest.getCity());
                company.setCompanyAddress(companyRequest.getCompanyAddress());
                company.setCompanyType(companyRequest.getCompanyType());
                company.setEmployeeSize(companyRequest.getEmployeeSize());
                company.setMobileNo(companyRequest.getMobileNo());
                company.setPinCode(companyRequest.getPinCode());
                company.setState(companyRequest.getState());
                company.setWebsite(companyRequest.getWebsite());
                companyRepository.save(company);
                String companyId = company.getCompanyId().toString();
                String code = this.generateCompanyCode(companyRequest.getCompanyName(), companyId);
                company.setCompanyCode(code);
                System.out.println(" companyCode is " + code);
                companyRepository.save(company);
                return " updated..";
            } else {
                Company company = new Company();
                company.setCompanyName(companyRequest.getCompanyName());
                company.setCity(companyRequest.getCity());
                company.setCompanyAddress(companyRequest.getCompanyAddress());
                company.setCompanyType(companyRequest.getCompanyType());
                company.setEmployeeSize(companyRequest.getEmployeeSize());
                company.setMobileNo(companyRequest.getMobileNo());
                company.setPinCode(companyRequest.getPinCode());
                company.setState(companyRequest.getState());
                company.setWebsite(companyRequest.getWebsite());
                companyRepository.save(company);
                String companyId = company.getCompanyId().toString();
                String code = this.generateCompanyCode(companyRequest.getCompanyName(), companyId);
                company.setCompanyCode(code);
                System.out.println(" companyCode is " + code);
                companyRepository.save(company);
                return "saved..";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getAllCompany(Pageable pageable, String search) {
        Page<CompanyProjection> projections;
        if (search != null) {
            projections = companyRepository.getCompanyNameContainingIgnoreCase(pageable, search);
        } else {
            projections = companyRepository.getAllCompany(pageable);
        }
        return new PageDto(projections.getContent(), projections.getTotalPages(), projections.getTotalElements(), projections.getNumber());
    }

    @Override
    public Object deleteByCompanyId(Long companyId) throws Exception {
        if (companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
            return "deleted..";
        } else {
            throw new Exception("companyId is not found");
        }
    }

    @Override
    public Object importExcel(MultipartFile file) throws Exception {
        try {

            if (file == null || file.isEmpty()) {
                throw new Exception(" file is null");
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            System.out.println("filename is: " + fileName + " extension is: " + extension);

            if (extension.equalsIgnoreCase("xlsx") || extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("xls")) {
                if (fileName.contains("..")) {
                    throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
                }
               FileInputStream fis = new FileInputStream("C:\\Users\\OMS\\Downloads\\document.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet worksheet = workbook.getSheetAt(0);
                for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                    if (index > 0) {
                        XSSFRow row = worksheet.getRow(index);
                        DataFormatter dataFormatter = new DataFormatter();

                        String companyName = " ";
                        String companyCode="";
                        String companyAddress = " ";
                        String pinCode = " ";
                        String mobileNo = " ";
                        String state = " ";
                        String city = " ";
                        String website = " ";
                        String employeeSize = " ";
                        String companyType = " ";

                        if (row.getCell(0) != null) {
                            companyName = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            companyName = companyName.trim();
                        } if (row.getCell(0) != null) {
                            companyCode = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            companyCode = companyCode.trim();
                        }
                        if (row.getCell(1) != null) {
                            companyAddress = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            companyAddress = companyAddress.trim();
                        }
                        if (row.getCell(2) != null) {
                            pinCode = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            pinCode = pinCode.trim();
                        }
                        if (row.getCell(3) != null) {
                            mobileNo = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            mobileNo = mobileNo.trim();
                        }
                        if (row.getCell(4) != null) {
                            state = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            state = state.trim();
                        }
                        if (row.getCell(5) != null) {
                            city = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            city = city.trim();
                        }
                        if (row.getCell(6) != null) {
                            website = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            website = website.trim();
                        }
                        if (row.getCell(7) != null) {
                            employeeSize = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            employeeSize = employeeSize.trim();
                        }
                        if (row.getCell(8) != null) {
                            companyType = String.valueOf(Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null));
                            companyType = companyType.trim();
                        }

                        Company company = companyRepository.findByCompanyCode(companyCode);
                        if (company==null) {
//                            Company company1 = new Company();
                        }
                        company.setCity(city);
                        company.setCompanyName(companyName);
                        company.setCompanyType(companyType);
                        company.setEmployeeSize(employeeSize);
                        company.setMobileNo(mobileNo);
                        company.setWebsite(website);
                        company.setState(state);
                        company.setPinCode(pinCode);
                        company.setCompanyAddress(companyAddress);

                        companyRepository.save(company);
                    }
                    return "uploaded..";
                }
                //workbook.close();
                //fis.close();
            } else {
                throw new Exception("unsupported Extension found");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object importExcelFile(MultipartFile file) throws Exception {

      return null;
     }

    @Override
    public InputStream exportProduct() {
         List<CompanyProjection> projections = new ArrayList<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        projections=companyRepository.getAllCompanys();

        return null;
    }

    public String generateCompanyCode(String companyName,String companyId) throws Exception {
        if (companyName!=null){
            return companyName.substring(0,Math.min(companyName.length(),3)).toUpperCase()+number+companyId;
        }else {
            throw new Exception("company name can not be empty");
        }
    }
}
