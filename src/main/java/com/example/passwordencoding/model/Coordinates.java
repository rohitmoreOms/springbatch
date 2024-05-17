package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Coordinates {

    @Id
 private Long coordinatesId;
    private String latitude;
    private String longitude;


}
