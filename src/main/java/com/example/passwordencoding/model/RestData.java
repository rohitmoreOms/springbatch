package com.example.passwordencoding.model;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Dataset")
public class RestData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restId;
    private Double price;
    private Long productId;
    private String image;
    private Integer quantity;
    private Double subTotal;
    private Long cartId;
    private String productTitle;
}
