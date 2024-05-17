package com.example.passwordencoding.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter
@Getter
@ToString
public class Result {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Name> name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> location;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Login> login;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Dob> dob;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Registered> registered;

    private String phone;

    private String cell;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Id> id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> picture;


}
