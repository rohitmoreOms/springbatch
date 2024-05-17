package com.example.passwordencoding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDto {
        private Object dataSet;
        private Integer totalPages;
        private Long totalElements;
        private int number;
}
