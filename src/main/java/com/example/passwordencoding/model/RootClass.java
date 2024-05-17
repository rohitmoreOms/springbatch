package com.example.passwordencoding.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "root_class")
public class RootClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "root_class_id")
    private long rootClassId;

    @Column(name = "user_id")
    private int userId;
    private int id;
    private String title;
    private String body;
}
