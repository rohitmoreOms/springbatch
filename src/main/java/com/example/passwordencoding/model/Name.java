package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Name {

    @Id
    private Long nameId;
    private String title;
    private String first;
    private String last;




}
