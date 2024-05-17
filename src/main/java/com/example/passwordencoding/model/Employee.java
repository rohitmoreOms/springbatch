package com.example.passwordencoding.model;

import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE employee_id=?")
@Where(clause = "deleted=false")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "last_name")
    private String employeeLastName;

    @Column(name = "employee_address")
    private String address;

    @Column(name = "employee_jobrole")
    private String jobRole;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private String salary;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    private String employeeCode;

    private String file;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean isActive=Boolean.TRUE;

    private boolean deleted=Boolean.FALSE;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

}
