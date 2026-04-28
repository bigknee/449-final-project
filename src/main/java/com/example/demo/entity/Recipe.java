package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    private String title;
    private String description;
    private String ingredients;
    private int prepTime;
    private String category;

    private String userID; //many to many relationship: user owns many recipes

}
