package com.example.usingthirdpartyapidemo.exceptions;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
