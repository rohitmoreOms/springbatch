package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Registered {

    @Id
    private Long registerId;
    private String date;
    private int age;



}
