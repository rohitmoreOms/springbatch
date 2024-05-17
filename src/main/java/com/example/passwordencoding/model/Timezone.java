package com.example.passwordencoding.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Timezone {

    @Id
    private Long timeId;
    private String offset;
    private String description;



}
