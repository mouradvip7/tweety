package com.example.tweety.dto;

import lombok.Data;

@Data
public class ErrorDTO {

    private Integer statusCode;
    private String message;

}