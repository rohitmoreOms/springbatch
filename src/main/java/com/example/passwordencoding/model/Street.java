package com.example.passwordencoding.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Street {

    @Id
    private Long streetId;
    private int number;
    private String name;

}
