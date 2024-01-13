package com.example.usingthirdpartyapidemo.InheritanceDemo.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbc_Mentor")
public class Mentor extends User{
    @Id
    private Long id;
    private  String rating;
}
