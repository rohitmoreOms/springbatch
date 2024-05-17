package com.example.passwordencoding.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Setter
@Getter
public class LoginRequest {
    private String password;
    private String email;
    //private String fcmToken;
}
