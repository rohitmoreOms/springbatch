package com.example.passwordencoding.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "micro_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;


    private String name;

    @NotNull(message = "Email connot be null")
    @Size(min = 5,max = 50,message = "Email must be between 5 and 50 characters")
    private String email;


    private String about;

    Boolean isDeleted=false;
    @Transient
    private List<Rating>ratings =new ArrayList<>();
}
