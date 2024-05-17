package com.example.passwordencoding.Controller;

import com.example.passwordencoding.model.request.EmployeeRequest;
import com.example.passwordencoding.service.EmployeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeInterface employeeInterface;


    @PostMapping("/saveOrUpdateEmp")
    public ResponseEntity<?> saveOrUpdateEmp(@ModelAttribute EmployeeRequest employeeRequest,
                                             @RequestParam Long companyId) {
        try {
            return new ResponseEntity(employeeInterface.saveOrUpdateEmp(employeeRequest,companyId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteByEmployeeId")
    public ResponseEntity<?>deleteByEmployeeId(@RequestParam Long employeeId) {
        try {
            return new ResponseEntity(employeeInterface.deleteByEmployeeId(employeeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("getAllEmployee")
    public ResponseEntity<?> getAllEmployee(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize ,
                                            @RequestParam(required = false) String search) {
        try {
            Pageable pageable= PageRequest.of(Optional.ofNullable(pageNo).orElse(0),Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(employeeInterface.getAllEmployee(pageable,search ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/getEmployeeByCompany")
    public ResponseEntity<?>getEmployeeByCompany(@RequestParam(required = false) String companyName) {
        try {
            return new ResponseEntity(employeeInterface.getEmployeeByCompany(companyName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/getEmployeeNameByEmployeeId")
    public ResponseEntity<?>getEmployeeNameByEmployeeId(@RequestParam Long employeeId) {
        try {
            return new ResponseEntity(employeeInterface.getEmployeeNameByEmployeeId(employeeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/searchUserName")
    public ResponseEntity<?>searchUserName(@RequestParam(required = false) String userName) {
        try {
            return new ResponseEntity(employeeInterface.searchUserName(userName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
//    @PostMapping("/saveOrUpdate")
//    public ResponseEntity<?> saveOrUpdate(@ModelAttribute EmployeeRequest employeeRequest, @RequestParam Long companyId) {
//        try {
//            return new ResponseEntity<>(new EntityResponse( employeeInterface.saveOrUpdate(employeeRequest,companyId),0),HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(),-1), HttpStatus.OK);
//        }
//    }
}
