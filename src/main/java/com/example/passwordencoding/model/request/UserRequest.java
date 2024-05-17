package com.example.passwordencoding.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserRequest {
    private Long userId;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Email connot be null")
    @Size(min = 5,max = 50,message = "Email must be between 5 and 50 characters")
    private String email;

    @NotNull(message = "About not null")
    @Size(min = 1,max = 100,message = "About must be between 1 and 100 characters")
    private String about;
}
