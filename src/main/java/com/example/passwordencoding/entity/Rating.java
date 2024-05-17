package com.example.passwordencoding.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private  String feedback;

}
