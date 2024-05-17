package com.example.passwordencoding.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "company")
@SQLDelete(sql = "UPDATE company SET deleted = true WHERE company_id=?")
@Where(clause = "deleted=false")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_pincode")
    private String pinCode;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "website")
    private String website;

    @Column(name = "employee_size")
    private String employeeSize;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "company_code")
    private String companyCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean isActive=Boolean.TRUE;

    private boolean deleted=Boolean.FALSE;

}
