package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Id {


    @javax.persistence.Id
    private Long id;
    private String name;
    private String value;



}
