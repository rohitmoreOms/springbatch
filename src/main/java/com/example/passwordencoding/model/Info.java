package com.example.passwordencoding.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Info {


    @Id
    private Long infoId;
    private String seed;
    private int results;
    private int page;
    private String version;

    @OneToOne
    private Root root;

}
