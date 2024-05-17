package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Getter
@Setter
public class Dob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dobId;
    private Date date;
    private int age;



}
