package com.example.passwordencoding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Long apiResponseId;

    @JsonProperty("response")
    private ResponseData responseData;

    @JsonProperty("status")
    private Integer status;
}
