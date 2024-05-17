package com.example.passwordencoding.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomEntityResponse {
    private String response;
    private Integer status;
}