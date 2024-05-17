package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Location {

    @Id
    private Long locationId;
    private String city;
    private String state;
    private String country;
    private int postcode;

    @OneToOne
    private Street street;

   @OneToOne
    private Coordinates coordinates;

  @OneToOne
    private Timezone timezone;


}
