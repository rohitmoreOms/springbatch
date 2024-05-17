package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Root {

    @Id
    private Long rootId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_id")
    private List<Result> results;

    @OneToOne
    private Info info;




}
