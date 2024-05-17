package com.example.passwordencoding.Controller;

import com.example.passwordencoding.model.request.CompanyRequest;
import com.example.passwordencoding.service.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    CompanyInterface companyInterface;

    @PostMapping("/saveOrUpdateCompany")
    public ResponseEntity<?> saveOrUpdateCompany(@ModelAttribute CompanyRequest companyRequest) {
        try {
            return new ResponseEntity(companyInterface.saveOrUpdateCompany(companyRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/getAllCompany")
    public ResponseEntity<?>getAllCompany(@RequestParam(name = "pageNo",defaultValue = "0")Integer pageNo,
                                          @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize,
                                          @RequestParam(required = false) String search) {
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNo).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(companyInterface.getAllCompany(pageable,search), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteByCompanyId")
    public ResponseEntity<?>deleteByCompanyId(@RequestParam Long companyId) {
        try {
            return new ResponseEntity(companyInterface.deleteByCompanyId(companyId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/importExcel")
    public ResponseEntity<?>importExcel(@RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity(companyInterface.importExcel(file), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/importExcelFile")
    public ResponseEntity<?>importExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity(companyInterface.importExcelFile(file), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

     @GetMapping("exportExcel")
 public ResponseEntity<?>exportExcel(@RequestParam String extension){

         String fileName="";
         if (extension.equalsIgnoreCase("xlsx")){
             fileName="product_report.xlsx";
         }else if (extension.equalsIgnoreCase("csv")){
             fileName="product_report.csv";
         }else if (extension.equalsIgnoreCase("xls")){
             fileName="product_report.xls";
         }else {
             fileName="product_report.xlsx";
         }

         InputStreamResource file = new InputStreamResource(companyInterface.exportProduct());

         return ResponseEntity.ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                 .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                 .contentType(MediaType.parseMediaType("application/csv")).body(file);


     }
}
