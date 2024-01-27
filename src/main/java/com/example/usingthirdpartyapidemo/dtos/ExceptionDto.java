package com.example.usingthirdpartyapidemo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {
    //this is common exception DTO class for all exceptions
    private  String message;
    private  String detail;
}
