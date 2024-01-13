package com.example.usingthirdpartyapidemo.InheritanceDemo.mappedSuperClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ma_Instructor")
public class Instructor extends User {
    private String favouriteStudent;
}
