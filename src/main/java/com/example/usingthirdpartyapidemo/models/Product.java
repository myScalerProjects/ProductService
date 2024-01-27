package com.example.usingthirdpartyapidemo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel {

    private String title;
    private  Double price;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})

    private Category category;
    private  String imageURL;

}
