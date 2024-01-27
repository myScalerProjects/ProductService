package com.example.usingthirdpartyapidemo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private  String categoryName;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonIgnore
  //  @JsonManagedReference
    private List<Product> productList;
    private String description;
}
