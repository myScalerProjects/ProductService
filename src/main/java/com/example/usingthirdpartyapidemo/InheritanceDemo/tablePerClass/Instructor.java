package com.example.usingthirdpartyapidemo.InheritanceDemo.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbc_Instructor")
public class Instructor extends User{
    @Id
    private Long id;
    private String favouriteStudent;
}
