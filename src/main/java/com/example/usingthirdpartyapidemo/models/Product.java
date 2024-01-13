package com.example.usingthirdpartyapidemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel {

    private String title;
    private  double price;
    private String description;
    @ManyToOne
    private Category category;
    private  String imageURL;

}
