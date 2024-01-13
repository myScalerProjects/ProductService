package com.example.usingthirdpartyapidemo.InheritanceDemo.mappedSuperClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ma_Mentor")
public class Mentor extends User {

    private  String rating;
}
