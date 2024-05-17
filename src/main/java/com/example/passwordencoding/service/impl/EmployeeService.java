package com.example.passwordencoding.service.impl;

import com.example.passwordencoding.model.Company;
import com.example.passwordencoding.model.Employee;
import com.example.passwordencoding.model.PageDto;
import com.example.passwordencoding.model.request.EmployeeRequest;
import com.example.passwordencoding.model.response.OtpResponse;
import com.example.passwordencoding.repository.CompanyRepository;
import com.example.passwordencoding.repository.EmployeeRepository;
import com.example.passwordencoding.repository.EmployeeRepositoryImpl;
import com.example.passwordencoding.repository.projection.EmployeeProjection;
import com.example.passwordencoding.service.EmployeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeService implements EmployeeInterface {
    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    EmployeeRepositoryImpl employeeRepositoryImpl;
    private static String number = " 0101";

    private static final long OTP_EXPIRATION_DURATION = TimeUnit.MINUTES.toMillis(1); // Set the expiration duration to 5 minutes

    @Override
    public Object saveOrUpdateEmp(EmployeeRequest employeeRequest, Long companyId) throws Exception {
        try {
            if (employeeRequest != null) {
                if (employeeRepository.existsById(employeeRequest.getEmployeeId())) {
                    Employee employee = employeeRepository.findById(employeeRequest.getEmployeeId()).get();
                    Company company = companyRepository.findById(companyId).get();
                    employee.setAddress(employeeRequest.getAddress());
                    employee.setJobRole(employeeRequest.getJobRole());
                    employee.setEmployeeName(employeeRequest.getEmployeeName());
                    employee.setEmail(employeeRequest.getEmail());
                    employee.setSalary(employeeRequest.getSalary());
                    employee.setMobileNo(employeeRequest.getMobileNo());
                    employee.setPinCode(employeeRequest.getPinCode());
                    employee.setState(employeeRequest.getState());
                    employee.setCity(employeeRequest.getCity());
                    employee.setCountry(employeeRequest.getCountry());
                    employee.setEmployeeLastName(employeeRequest.getEmployeeLastName());
                    employee.setCompany(company);
                    if (employeeRequest.getFile() != null) {
                        employee.setFile((String) this.uploadFile(employeeRequest.getFile()));
                    }
                    employeeRepository.save(employee);
                    String employeeId = employee.getEmployeeId().toString();
                    String code = this.generateEmployeeCode(employeeRequest.getEmployeeName(), employeeId);
                    employee.setEmployeeCode(code);
                    employeeRepository.save(employee);
                    return "updated..";
                } else {
                    Employee employee = new Employee();
                    if (companyRepository.existsById(companyId)) ;
                    {
                        Company company = companyRepository.findById(companyId).get();
                        employee.setCompany(company);
                    }
                    employee.setEmployeeName(employeeRequest.getEmployeeName());
                    employee.setAddress(employeeRequest.getAddress());
                    employee.setJobRole(employeeRequest.getJobRole());
                    employee.setEmail(employeeRequest.getEmail());
                    employee.setSalary(employeeRequest.getSalary());
                    employee.setMobileNo(employeeRequest.getMobileNo());
                    employee.setPinCode(employeeRequest.getPinCode());
                    employee.setState(employeeRequest.getState());
                    employee.setCity(employeeRequest.getCity());
                    employee.setCountry(employeeRequest.getCountry());
                    employee.setEmployeeLastName(employeeRequest.getEmployeeLastName());
                    if (employeeRequest.getFile() != null) {
                        employee.setFile(this.uploadFile(employeeRequest.getFile()));
                    }
                    employeeRepository.save(employee);

                    String employeeId = employee.getEmployeeId().toString();
                    String code = this.generateEmployeeCode(employeeRequest.getEmployeeName(), employeeId);
                    employee.setEmployeeCode(code);
                   String otp=this.generateOtp().toString();
                    employeeRepository.save(employee);
                   mailSender(employeeRequest.getEmail(), "Notification", "your otp is " +otp);
                    return "saved..";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object deleteByEmployeeId(Long employeeId) throws Exception {
        return employeeRepository.findById(employeeId).map(employee -> {
            employeeRepository.deleteById(employeeId);
            return "deleted";
        }).orElseThrow(() -> new Exception("id not exist"));
        /*if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return "deleted..";
        } else {
            throw new Exception("employeeId is not found");
        }
         */
    }

    @Override
    public Object getAllEmployee(Pageable pageable, String search) {
        Page<EmployeeProjection> projections;
        if (search != null && !search.isEmpty()) {
            projections = employeeRepository.getEmployeeNameContainingIgnoreCase(pageable, search);
        } else {
            projections = employeeRepository.getAllEmployee(pageable);
        }
        return new PageDto(projections.getContent(), projections.getTotalPages(), projections.getTotalElements(), projections.getNumber());
    }

    @Override
    public Object getEmployeeByCompany(String companyName) throws Exception {
        if (companyRepository.existsByCompanyName(companyName)) {
            List<Employee> employee = employeeRepository.getEmployeeNameByCompanyName(companyName);
            return employee;
        } else {
            throw new Exception("company not found");
        }
    }

    @Override
    public Object getEmployeeNameByEmployeeId(Long employeeId) {
        List<Employee> employees = employeeRepositoryImpl.getEmployeeNameByEmployeeId(employeeId);
        return null;
    }

    @Override
    public Object searchUserName(String userName) {
        List<Employee> employees = employeeRepositoryImpl.searchEmployee(userName);

        return employees;
    }

    @Override
    public Object saveOrUpdate(EmployeeRequest employeeRequest, Long companyId) {

        return null;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String uploadPath = "E:\\Rohit_Project";
                Path filePath = Paths.get(uploadPath, originalFilename);
                Files.write(filePath, file.getBytes());
                return originalFilename;
            } catch (IOException e) {
                throw new Exception("Failed to upload file: " + e.getMessage());
            }
        } else {
            throw new Exception("File not uploaded");
        }
    }

    public String generateEmployeeCode(String employeeName, String employeeId) throws Exception {
        if (employeeName != null) {
            return employeeName.substring(0, Math.min(employeeName.length(), 3)).toUpperCase() + number + employeeId;
        } else {
            throw new Exception("employeeName Can't be empty");
        }
    }

    public void mailSender(String toEmail, String subject, String body) {
        //     JavaMailSender javaMailSender = null;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);

    }

    public void sendOtpByEmail(String otpCode, long expirationTime) {
        String emailBody = "Your OTP is: " + otpCode + ". It will expire on " + new Date(expirationTime);
        EmployeeRequest employeeRequest = new EmployeeRequest();
        // Replace 'toEmail', 'Subject', and 'body' with your actual email parameters
        String toEmail = employeeRequest.getEmail();
        String subject = "OTP Notification";
        String body = emailBody;

        mailSender(toEmail, subject, body);
    }

    public String generateRandomOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        String randomOtp = String.valueOf(otp);
        return randomOtp;
    }

    public OtpResponse generateOtp() {
        String otpCode = generateRandomOtp();
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + OTP_EXPIRATION_DURATION;

        OtpResponse otp = new OtpResponse(otpCode, expirationTime);
        otp.setCode(otpCode);
        otp.setExpirationTime(expirationTime);

        sendOtpByEmail(otpCode, expirationTime);
        // Save the OTP and expiration time in your data store or send it via email
        return otp;
    }
}
