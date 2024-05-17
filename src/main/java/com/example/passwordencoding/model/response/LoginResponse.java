package com.example.passwordencoding.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
   private String token;
    private Long teamId;
    // private Boolean isEmailVerified;
    // private Boolean isMobileVerified;
    private String name;
    private String userType;
    private String accessType;
    private String profileImage;
}