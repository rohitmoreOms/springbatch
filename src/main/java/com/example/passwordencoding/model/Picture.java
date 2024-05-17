package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Picture {

    @Id
    private Long pictureId;
    private String large;
    private String medium;
    private String thumbnail;



}
