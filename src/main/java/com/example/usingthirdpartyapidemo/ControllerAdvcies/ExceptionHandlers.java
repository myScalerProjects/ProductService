package com.example.usingthirdpartyapidemo.ControllerAdvcies;

import com.example.usingthirdpartyapidemo.dtos.ArithmeticExceptionDto;
import com.example.usingthirdpartyapidemo.dtos.ExceptionDto;
import com.example.usingthirdpartyapidemo.exceptions.CategoryNotFoundException;
import com.example.usingthirdpartyapidemo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException()
    {
        ArithmeticExceptionDto response=new ArithmeticExceptionDto();
        response.setMessage("something went wrong");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBound()
    {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException exception)
    {
        ExceptionDto dto= new ExceptionDto();
        dto.setMessage(exception.getMessage());;
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException exception)
    {
        ExceptionDto dto= new ExceptionDto();
        dto.setMessage(exception.getMessage());;
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}
