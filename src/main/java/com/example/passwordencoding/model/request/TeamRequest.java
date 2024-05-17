package com.example.passwordencoding.model.request;

import com.example.passwordencoding.service.util.AcessType;
import com.example.passwordencoding.service.util.UserType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TeamRequest {
    private Long teamId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserType userType;
    private AcessType accessType;
}
